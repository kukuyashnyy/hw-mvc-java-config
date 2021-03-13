package org.example.dao;

import org.example.domain.entity.Post;
import org.example.domain.entity.PostTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class PostDaoMySqlImpl implements PostDao {
    private final DataSource dataSource;

    @Autowired
    public PostDaoMySqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public PostTransfer findPostDatabase(PostTransfer postTransfer) {
        int count = 0;
        List<Post> postList = new ArrayList<>();
        int startPosition = postTransfer.getStartPosition();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from posts where draft = 'no' limit " + startPosition + ", " + 3 + ";");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String postAuthor = resultSet.getString("postAuthor");
                String publicationDate = resultSet.getString("publicationDate");
                String postName = resultSet.getString("postName");
                String postTheme = resultSet.getString("postTheme");
                String postBody = null;
                String draft = null;
                Post post = new Post(postAuthor, publicationDate, postName, postTheme, postBody, draft);
                post.setId(id);
                postList.add(post);
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PostTransfer postTransfer1 = new PostTransfer();
        postTransfer1.setPostList(postList);
        postTransfer1.setCountLines(countLines());
        postTransfer1.setStartPosition(startPosition);
        postTransfer1.setPostPosition(startPosition + count);
        return postTransfer1;
    }


    @Override
    public Integer countLines() {
        int countLines = 0;
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet1 = statement.executeQuery("select count(*) from posts where draft = 'no';");
            if (resultSet1.next()) {
                countLines = resultSet1.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return countLines;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Integer save(Post data) throws SQLException {
        int id = 0;
        Post post = data;
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            if (post.getPublicationDate().equals("")) {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into posts (postAuthor, postName, postTheme, post, draft) values(?,?,?,?,?)");
                    preparedStatement.setString(1, post.getPostAuthor());
                    preparedStatement.setString(2, post.getPostName());
                    preparedStatement.setString(3, post.getPostTheme());
                    preparedStatement.setString(4, post.getPostBody());
                    preparedStatement.setString(5, post.getDraft());
                    preparedStatement.execute();
                    connection.setAutoCommit(true);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select id from posts order by id desc limit 1;");
                    while (resultSet.next()) {
                        id = resultSet.getInt(1);
                    }
                } catch (Exception exception) {
                    connection.rollback();
                    exception.printStackTrace();
                }
            } else {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into posts (postAuthor, publicationDate ,postName, postTheme, post, draft) values (?,?,?,?,?,?);");
                    preparedStatement.setString(1, post.getPostAuthor());
                    preparedStatement.setString(2, post.getPublicationDate());
                    preparedStatement.setString(3, post.getPostName());
                    preparedStatement.setString(4, post.getPostTheme());
                    preparedStatement.setString(5, post.getPostBody());
                    preparedStatement.setString(6, post.getDraft());
                    preparedStatement.execute();
                    connection.setAutoCommit(true);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select id from posts order by id desc limit 1;");
                    while (resultSet.next()) {
                        id = resultSet.getInt(1);
                    }
                } catch (Exception exception) {
                    connection.rollback();
                    exception.printStackTrace();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Post> findAll() throws SQLException {
        return null;
    }

    @Override
    public Post findById(Integer integer) throws SQLException {
        Post post = null;
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from posts where  id = '" + integer + "';");
            while (resultSet.next()) {
                String postAuthor = resultSet.getString("postAuthor");
                String publicationDate = resultSet.getString("publicationDate");
                String postName = resultSet.getString("postName");
                String postTheme = resultSet.getString("postTheme");
                String postBody = resultSet.getString("post");
                String draft = resultSet.getString("draft");
                int id = resultSet.getInt("id");
                post = new Post(postAuthor, publicationDate, postName, postTheme, postBody, draft);
                post.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return post;
    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }

    @Override
    public Post update(Post data) throws SQLException {
        return null;
    }
}
