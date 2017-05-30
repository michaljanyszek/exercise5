package wdsr.exercise5.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import wdsr.exercise5.model.Trade;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public class TradeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Zaimplementuj metode insertTrade aby wstawiała nowy rekord do tabeli "trade"
     * na podstawie przekazanego objektu klasy Trade.
     * @param trade
     * @return metoda powinna zwracać id nowego rekordu.
     */
    public int insertTrade(Trade trade) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sqlString = "INSERT INTO Trade (asset, amount, date) VALUES (?, ?, ?)";
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlString, PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setString(1, trade.getAsset());
                ps.setDouble(2, trade.getAmount());
                ps.setDate(3, new Date(trade.getDate().getTime()));
                
                return ps;
            }
        }, keyHolder);
        
        return (int) keyHolder.getKey();
    }

    /**
     * Zaimplementuj metode aby wyciągneła z bazy rekord o podanym id.
     * Użyj intrfejsu RowMapper.
     * @param id
     * @return metaoda powinna zwracać obiekt reprezentujący rekord o podanym id.
     */
    public Optional<Trade> extractTrade(int id) {
        String sqlString = "SELECT * FROM TRADE WHERE ID="+id;
        
        List<Trade> trades  = jdbcTemplate.query(sqlString,new BeanPropertyRowMapper<Trade>(Trade.class));
        
        if(trades.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(trades.get(0));
    }

    /**
     * Zaimplementuj metode aby wyciągneła z bazy rekord o podanym id.
     * @param id, rch - callback który przetworzy wyciągnięty wiersz.
     * @return metaoda powinna zwracać obiekt reprezentujący rekord o podanym id.
     */
    public void extractTrade(int id, RowCallbackHandler rch) {
        String sqlString = "SELECT * FROM TRADE WHERE ID="+id;
        
        jdbcTemplate.query(sqlString, rch);
    }

    /**
     * Zaimplementuj metode aby zaktualizowała rekord o podanym id danymi z przekazanego parametru 'trade'
     * @param trade
     */
    public void updateTrade(int id, Trade trade) {
        String sqlString = "UPDATE TRADE SET ASSET = ?, AMOUNT = ?, DATE = ? WHERE ID = ?";
        Object[] params = new Object[] { trade.getAsset(), trade.getAmount(), trade.getDate(),id };
        int[] types = { Types.VARCHAR, Types.DOUBLE, Types.DATE,Types.INTEGER  };
        jdbcTemplate.update(sqlString, params, types);
    }

    /**
     * Zaimplementuj metode aby usuwała z bazy rekord o podanym id.
     * @param id
     */
    public void deleteTrade(int id) {
        String sqlString = "DELETE FROM TRADE WHERE ID = ?";
        jdbcTemplate.update(sqlString, id);
    }

}
