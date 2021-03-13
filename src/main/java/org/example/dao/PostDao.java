package org.example.dao;

import org.example.dao.GenericDao;
import org.example.domain.entity.Post;
import org.example.domain.entity.PostTransfer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PostDao extends GenericDao<Post, Integer> {

    PostTransfer findPostDatabase(PostTransfer postTransfer) throws SQLException;
    Integer countLines()throws SQLException;
    Connection getConnection() throws SQLException;
}
