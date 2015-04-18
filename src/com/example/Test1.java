package com.example;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Session session=HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try{

	        Location location = new Location();
	        location.setName("India");
	        location.getAddress().setStreetAddress("St.");
	        location.getAddress().setCity("Hyderabad");
	        location.getAddress().setState("AP");
	        location.getAddress().setZipCode("200001");
	
	        Event event = new Event();
	        event.setName("Annual Meeting");
	        event.setDuration(60);
	        event.setStartDate(createDate(2009, 11, 1));
	        event.setLocation(location);

	        //session.save(location);
	        session.save(event);
	        tx.commit();
        
	        System.out.println("rows added");
		
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
	        session.close();
		}
        
	}
	
	 private static Date createDate(int year, int month, int day) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(year, month, day);
	        return calendar.getTime();
	 }

}
