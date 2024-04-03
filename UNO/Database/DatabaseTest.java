package Database;

import static org.junit.Assert.*;

import org.junit.*;

public class DatabaseTest {

	private Database database;
	
	private int randno;
	
	@Before
	public void init()
	{
		
		// Set up the database connection.
		database = new Database(".\\Database\\db.properties");
		
		// Create a random number between one and ten.
		randno = ((int)Math.random() % 11);
		
	}
	
	@Test
	public void connectivityTest()
	{
		
		// This test ensures that the Database object successfully connected.
		assertNotNull("Faild: Connection was returned as null.", database.getConnection());
		
	}
	
	@Test
	public void successfulLogin()
	{
		
		// This test ensures that a user with valid login credentials can successfully log into their account.
	
		// Declare variables.
		String username = "Admin" + (randno + 1);
		String password = "Password" + (randno + 1);
		boolean result = false;
		
		// Prompt the database for the user.
		result = database.verifyAccount(new LoginData(username, password));
	
		assertEquals("Failed: Valid login credentials were not verified.", result, true);
		
	}
	
	@Test
	public void invalidLogin()
	{
		
		// This test ensures that a user with invalid login credentials cannot successfully log into an account.
		String username = "Admin" + randno;
		String password = "InvalidPW";
		boolean result = false;
		
		// Prompt the database for the user.
		result = database.verifyAccount(new LoginData(username, password));
		
		assertEquals("Failed: Invalid login credentials were falsely verified.", result, false);
		
	}

	@Test
	public void createAccountSuccess()
	{
		
		// Declare variables.
		CreateUserAccount data = new CreateUserAccount();
		String username = "NewUser";
		String password = "Password11";
		boolean result = false;
		
		// Set up the account data.
		data.setUsername(username);
		data.setPassword(password);
		
		// Create the new user.
		result = database.createAccount(data);
		
		assertEquals("Failed: Unable to create user account. Ensure that the database does not already have a user with these credentials.", result, true);
		
	}
	
}
