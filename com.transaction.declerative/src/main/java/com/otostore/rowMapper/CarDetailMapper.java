package com.otostore.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.otostore.model.CarDetail;

public class CarDetailMapper implements RowMapper<CarDetail>{

	@Override
	public CarDetail mapRow(ResultSet result, int rowNum) throws SQLException {
		CarDetail carDetail=new CarDetail();
		
		carDetail.setId(result.getInt("ID"));
		carDetail.setMarka(result.getString("MARKA"));
		carDetail.setModel(result.getString("MODEL"));
		carDetail.setYear(result.getInt("YEAR"));
		carDetail.setAddDate(result.getDate("ADDDATE"));
		carDetail.setUpdateAddDate(result.getDate("UPDATEADDDATE"));
		
		return carDetail;
	}

}
