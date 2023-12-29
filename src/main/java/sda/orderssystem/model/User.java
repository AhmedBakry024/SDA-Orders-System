package sda.orderssystem.model;

public class User {

	private String name;
    private int id;
    private String address;
    private String password;
    private String email;
    private String phone;
    private int balance;

    public User(String name, int i, String phoneNum, String email, String password, String address) {
        
        this.name = name;
        this.id = i;
        this.phone = phoneNum;
        this.email = email;
        this.password = password;
        this.address = address;
        this.balance = 0;
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
