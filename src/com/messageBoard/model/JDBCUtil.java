package com.messageBoard.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class JDBCUtil {

    private static String url = "jdbc:mysql://localhost:3306/message";
    private static String name = "root";
    private static String password = "root";
    private static Connection connnection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static JDBCUtil jdbcUtil = null;

    //懒汉式加载
    public static synchronized JDBCUtil getInitJDBCUtil() {
        if (jdbcUtil == null) {
            jdbcUtil = new JDBCUtil();
        }
        return jdbcUtil;
    }

    public JDBCUtil() {

    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //得到数据库连接
    public Connection getConnection() {

        try {
            connnection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connnection;
    }

    //执行增加删除和修改
    public int executeUpdate(String sql) {
        int affectedLine = 0;
        try {
            preparedStatement = getConnection().prepareStatement(sql);
            affectedLine = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return affectedLine;
    }


    public int executeUpdate(String sql, Object[] params) {
        int affectedLine = 0;
        try {
            connnection = getConnection();
            preparedStatement = connnection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            affectedLine = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return affectedLine;
    }

    public ResultSet executeQueryRS(String sql) {
        connnection = getConnection();
        try {
            preparedStatement = connnection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultSet;
    }

    /**
     * @param sql
     * @param params
     * @return resultSet
     */
    private ResultSet executeQueryRS(String sql, Object[] params) {
        connnection = getConnection();

        try {
            preparedStatement = connnection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }

    public List<HashMap> executeQuery(String sql, Object[] params) {
        ResultSet rs = executeQueryRS(sql, params);
        //娑撳娼扮憰浣镐粵閻ㄥ嫬姘ㄩ弰顖濐洣閹跺s娑擃厾娈戦弫鐗堝祦閺�鎯у弳List閸掓銆冩稉锟�
        ResultSetMetaData rsmd = null;
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData(); //閸欐潧s娑擃厾娈戦崚锟�
            columnCount = rsmd.getColumnCount(); //閸欐牕鍨惃鍕殶閻╋拷
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayList<HashMap> list = new ArrayList<HashMap>();
        try {
            while (rs.next()) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return list;
    }


    public void closeAll() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (connnection != null) {
            try {
                connnection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
