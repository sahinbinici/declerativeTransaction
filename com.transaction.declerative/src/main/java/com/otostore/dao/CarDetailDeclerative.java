package com.otostore.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.otostore.model.CarDetail;
import com.otostore.rowMapper.CarDetailMapper;
import com.otostore.util.TimestampFactory;

@Transactional
public class CarDetailDeclerative extends JdbcDaoSupport{

	@Transactional
	public void insertTable(CarDetail carDetail){
		String query="INSERT INTO CarDetail (id,marka,model,year,addDate) VALUES(?,?,?,?,?)";
		getJdbcTemplate().update(query,new Object[] {carDetail.getId(),
													carDetail.getMarka(),
													carDetail.getModel(),
													carDetail.getYear(),
													carDetail.getAddDate()});
	}
	
	@Transactional(rollbackFor={Exception.class,RuntimeException.class})
	public void batchInsert(final List<CarDetail> carDetails){
		String query="INSERT INTO CarDetail (id,marka,model,year,addDate) VALUES(?,?,?,?,?)";
		
		getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				CarDetail carDetail=new CarDetail();
				ps.setInt(1, carDetail.getId());
				ps.setString(2, carDetail.getMarka());
				ps.setString(3, carDetail.getModel());
				ps.setInt(4, carDetail.getYear());
				ps.setTimestamp(5, TimestampFactory.getCurrenTimestamp());
				
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return carDetails.size();
			}
		});
	}
	
	@Transactional(rollbackFor=RuntimeException.class,isolation=Isolation.READ_COMMITTED)
	public void updateCarDetail(CarDetail carDetail){
		String query="UPDATE SET (marka=?,model=?,year=?,updateDate=?) WHERE id=?";
		getJdbcTemplate().update(query, new Object[] {carDetail.getMarka(),
														carDetail.getModel(),
														carDetail.getYear(),
														carDetail.getUpdateAddDate(),
														carDetail.getId()});
	}
	
	@Transactional(rollbackFor=RuntimeException.class,propagation=Propagation.REQUIRED,timeout=30)
	public void deleteCarDetail(int id){
		String query="DELETE FROM CarDetail WHERE id=?";
		getJdbcTemplate().update(query,id);
	}
	
	@Transactional(readOnly=true)
	public CarDetail getCarDetail(int id){
		String query="SELECT FROM CarDetail WHERE id=?";
		return getJdbcTemplate().queryForObject(query, new Object[] {id},new CarDetailMapper());
	}
	
	@Transactional(readOnly=true)
	public List<CarDetail> getAllCarDetail(){
		String query="SELECT FROM CarDetail";
		return (List<CarDetail>) getJdbcTemplate().query(query, new CarDetailMapper());
	}
	
}
