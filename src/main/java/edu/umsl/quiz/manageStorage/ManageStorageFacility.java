package edu.umsl.quiz.manageStorage;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import edu.umsl.quiz.dto.StorageFacility;

public class ManageStorageFacility 
{
	private static SessionFactory factory;
	
	public static void main (String[]args)
	{
		
		
		
		System.out.println("Enter the name of the storage facility: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		
		System.out.println("Enter the address of the facility you'd like to use: ");
		sc = new Scanner(System.in);
		String address = sc.next();
		
		System.out.println("Enter your phone number");
		sc = new Scanner(System.in);
		String phone = sc.next();
		
		System.out.println("Enter the date you'd like to book the unit on :");
		sc = new Scanner(System.in);
		String date = sc.next();
		
		//POSSIBLE QUESTIONS:
		//how to ask to put in more than one row of data?
		//how to show what is in the database?
		//how to delete?
		
		
		
		//Hibernate Code
		
				Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
				
				StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
				serviceRegistryBuilder.applySettings(configuration.getProperties());
				
				ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
				
				factory = new Configuration().configure().buildSessionFactory(serviceRegistry);
				
				//Hibernate Code
	addStore(name, address, phone, date);	
	//deleteStore(name, address,  phone, date);	
	//checkStore(name, address,  phone, date);
	}
	
	
public static long addStore(String name, String address, String phone, String date)	
{
	Session session = factory.openSession();
	Transaction tx = null;
	//long bookStorD = 0;
	
	long StoreFID = 0;
	
	try
	{
		tx = session.beginTransaction();
		StorageFacility aStore = new StorageFacility(name, address, phone, date);
		StoreFID = (long)session.save(aStore);
		tx.commit();		
	}
	catch (HibernateException e)
	{
		if(tx != null)tx.rollback();
		e.printStackTrace();
	}
	finally
	{
		session.close();
	}
	return StoreFID;
}
public static void deleteStore(String name, String address, String phone, String date)	
{
	Session session = factory.openSession();
	Transaction tx = null;
	
	
	try
	{
		tx = session.beginTransaction();
		StorageFacility dStore = new StorageFacility(name, address, phone, date);
		session.delete(dStore); //the return type is a void which is why we didn't set it up against anything.
		tx.commit();
		
		
	}
	catch (HibernateException e)
	{
		if(tx != null)tx.rollback();
		e.printStackTrace();
	}
	finally
	{
		session.close();
	}
	}

	
	public static void updateStore(String name, String address, String phone, String date)	
	{
		Session session = factory.openSession();
		Transaction tx = null;
	
		
		try
		{
			tx = session.beginTransaction();
			StorageFacility uStore = new StorageFacility(name, address, phone, date);
			session.update(uStore); //the return type is a void which is why we didn't set it up against anything.
			tx.commit();
			
			
		}
		catch (HibernateException e)
		{
			if(tx != null)tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
		public static StorageFacility checkStore(String name, String address, String phone, String date)	
		{
			Session session = factory.openSession();
			Transaction tx = null;
			StorageFacility cStore = null;
			//long bookStorD = 0;
			
			//long bookStoreID = addBookStore(name, address, phone, date);
			
			try
			{
				tx = session.beginTransaction();
				cStore = (StorageFacility) session.get(StorageFacility.class, new Integer(1)); 
				tx.commit();
				
				
			}
			catch (HibernateException e)
			{
				if(tx != null)tx.rollback();
				e.printStackTrace();
			}
			finally
			{
				session.close();
				
				
			}
			return cStore;
				
}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

