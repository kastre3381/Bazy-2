package com.bd2.bd2.service;

import com.bd2.bd2.model.GenealogyTree;
import com.bd2.bd2.model.XmlToTableResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Types;
import java.text.ParseException;
import java.util.List;

@Service
public class GenealogyTreeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<GenealogyTree> findAll() {
        String sql = "SELECT * FROM drzewo_genealogiczne";
        return jdbcTemplate.query(sql, new GenealogyTreeRowMapper());
    }

    public List<XmlToTableResults> getXmlToTableResults(Long id)
    {
        String sql = "SELECT * FROM dbo.xmlToTable(?)";
        return jdbcTemplate.query(sql, new XmlToTableRowMapper(), id);
    }

    public List<XmlToTableResults> getXmlToTableResultsFatherIndexes(Long id)
    {
        String sql = "SELECT * FROM dbo.xmlToTable(?) where plec='mezczyzna'";
        return jdbcTemplate.query(sql, new XmlToTableRowMapper(), id);
    }

    public List<XmlToTableResults> getXmlToTableResultsMotherIndexes(Long id)
    {
        String sql = "SELECT * FROM dbo.xmlToTable(?) where plec='kobieta'";
        return jdbcTemplate.query(sql, new XmlToTableRowMapper(), id);
    }

    public List<XmlToTableResults> getXmlToTableResultsFreePartnersIndexes(Long id)
    {
        String sql = "SELECT * FROM dbo.xmlToTable(?) where id_partnera is NULL";
        return jdbcTemplate.query(sql, new XmlToTableRowMapper(), id);
    }


    public void save(Long drzewoid,
                     String name,
                     String surname,
                     String bdate,
                     String ddate,
                     Long fid,
                     Long mid,
                     Long pid,
                     String plec) {
        if (fid == null || fid == 0) {
            fid = -1L;
        }

        if (mid == null || mid == 0) {
            mid = -1L;
        }

        if (pid == null || pid == 0) {
            pid = -1L;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = null, deathDate = null;
        try
        {
            birthDate = dateFormat.parse(bdate);
            deathDate = ddate == null ? null : dateFormat.parse(ddate);
        } catch (Exception e)
        {
            e.getStackTrace();
        }

        System.out.println(
                drzewoid + ", " +
                        name + ", " +
                        surname + ", " +
                        bdate + ", " +
                        ddate + ", " +
                        fid + ", " +
                        mid + ", " +
                        pid + ", " +
                        plec + ", " +
                        birthDate + ", " +
                        deathDate
                );

        String sql;
        if (deathDate == null) {
            sql = "EXEC dbo.dodaj_osobe_do_drzewa_c_bez_daty @DrzewoID=?, @imie=?, @nazwisko=?, @data_urodzenia=?, @id_ojca=?, @id_matki=?, @plec=?, @id_partner=?";
            jdbcTemplate.update(sql, drzewoid, name, surname, birthDate, fid, mid, plec, pid);
        } else {
            sql = "EXEC dbo.dodaj_osobe_do_drzewa_c @DrzewoID=?, @imie=?, @nazwisko=?, @data_urodzenia=?, @data_smierci=?, @id_ojca=?, @id_matki=?, @plec=?, @id_partner=?";
            jdbcTemplate.update(sql, drzewoid, name, surname, birthDate, deathDate, fid, mid, plec, pid);
        }
        System.out.println("Saved person");
    }


    public void deleteById(Long id) {
        String sql = "DELETE FROM drzewo_genealogiczne WHERE id = ?";
        jdbcTemplate.update(sql, id);
        System.out.println("Deleted genealogy tree with ID: " + id);
    }

    public void deletePerson(Long personId, Long treeId) {
        String sql = "EXEC dbo.usun_osobe_z_drzewa_c ?, ?";
        jdbcTemplate.update(sql, personId, treeId);
    }


    public GenealogyTree findById(Long id) {
        String sql = "SELECT * FROM drzewo_genealogiczne WHERE drzewo_id = ?";
        GenealogyTree genealogyTrees = jdbcTemplate.query(sql, new GenealogyTreeRowMapper(), id).get(0);
        System.out.println(genealogyTrees);
        return genealogyTrees;
    }



    private static class GenealogyTreeRowMapper implements RowMapper<GenealogyTree> {
        @Override
        public GenealogyTree mapRow(ResultSet rs, int rowNum) throws SQLException {
            GenealogyTree genealogyTree = new GenealogyTree();
            genealogyTree.setId(rs.getLong("drzewo_id"));
            genealogyTree.setXmlData(rs.getString("xmlData"));
            return genealogyTree;
        }
    }

    private static class XmlToTableRowMapper implements RowMapper<XmlToTableResults> {
        @Override
        public XmlToTableResults mapRow(ResultSet rs, int rowNum) throws SQLException {
            XmlToTableResults genealogyTree = new XmlToTableResults();
            genealogyTree.setId_osoby(rs.getLong("id_osoby"));
            genealogyTree.setImie(rs.getString("imie"));
            genealogyTree.setNazwisko(rs.getString("nazwisko"));
            genealogyTree.setData_urodzenia(rs.getString("data_urodzenia"));
            genealogyTree.setData_smierci(rs.getString("data_smierci"));
            genealogyTree.setPlec(rs.getString("plec"));
            genealogyTree.setId_ojca(rs.getLong("id_ojca"));
            genealogyTree.setId_matki(rs.getLong("id_matki"));
            genealogyTree.setId_partnera(rs.getLong("id_partnera"));
            return genealogyTree;
        }
    }
}
