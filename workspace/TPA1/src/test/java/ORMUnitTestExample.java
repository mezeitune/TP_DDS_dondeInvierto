
import org.hibernate.SessionFactory;
/*import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;*/
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;


/**
 * This template demonstrates how to develop a standalone test case for Hibernate ORM.  Although this is perfectly
 * acceptable as a reproducer, usage of ORMUnitTestCase is preferred!
 */
public class ORMUnitTestExample {


	private Configuration cfg;

	private SessionFactory sf;

	@Before
	public void setup() {
		/*cfg = new Configuration();

		// Add your entities here.
		// cfg.addAnnotatedClass(Foo.class);

		// Add in any settings that are specific to your test.  See resources/hibernate.properties for the defaults.
		cfg.setProperty("hibernate.show_sql", "true");
		cfg.setProperty("hibernate.format_sql", "true");
		cfg.setProperty("hibernate.hbm2ddl.auto", "update");

		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		ServiceRegistry sr = srb.build();
		sf = cfg.buildSessionFactory(sr);*/
		
		/*
		 * 		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder()
			// Add in any settings that are specific to your test. See resources/hibernate.properties for the defaults.
			.applySetting( "hibernate.show_sql", "true" )
			.applySetting( "hibernate.format_sql", "true" )
			.applySetting( "hibernate.hbm2ddl.auto", "update" );

		Metadata metadata = new MetadataSources( srb.build() )
		// Add your entities here.
		//	.addAnnotatedClass( Foo.class )
			.buildMetadata();

sf = metadata.buildSessionFactory();
		 * 
		 * */
}

	// Add your tests, using standard JUnit.

	
	
	
	
	
	
	@Test
	public void hhh123Test() throws Exception {

		
		
		
		
		
	}
}