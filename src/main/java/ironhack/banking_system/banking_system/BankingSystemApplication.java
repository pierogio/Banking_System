package ironhack.banking_system.banking_system;

import ironhack.banking_system.banking_system.embeddable.Address;
import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.models.accounts.CheckingAccount;
import ironhack.banking_system.banking_system.models.accounts.Savings;
import ironhack.banking_system.banking_system.models.accounts.StudentCheckingAccount;
import ironhack.banking_system.banking_system.models.users.AccountHolder;
import ironhack.banking_system.banking_system.models.users.Admin;
import ironhack.banking_system.banking_system.models.users.Role;
import ironhack.banking_system.banking_system.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BankingSystemApplication implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;

	@Autowired
	RolesRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AccountHolderRepository accountHolderRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CheckingAccountRepository checkingAccountRepository;

	@Autowired
	SavingsRepository savingsRepository;

	@Autowired
	StudentCheckingAccountRepository studentCheckingAccountRepository;


	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Admin admin = new Admin ( "SuperPiero", passwordEncoder.encode("Piero1"));
		userRepository.save(admin);
		roleRepository.save(new Role("ADMIN", admin));

		AccountHolder accountHolder = new AccountHolder();
		accountHolder.setName("SuperOscar");
		accountHolder.setPassword(passwordEncoder.encode("Oscar1"));
		accountHolder.setDateOfBirth(LocalDate.of(2004, 5, 7));
		accountHolder.setPrimaryAddress(new Address("Calle Sitges", "08000", "Salou"));
		accountHolder.setMailingAddress(new Address("Calle Oscarito que bonito", "08001", "Salou"));
		accountHolderRepository.save(accountHolder);
		roleRepository.save(new Role("USER", accountHolder));

		AccountHolder accountHolder2 = new AccountHolder();
		accountHolder2.setName("SuperQuim");
		accountHolder2.setPassword(passwordEncoder.encode("Quim1"));
		accountHolder2.setDateOfBirth(LocalDate.of(1998, 1, 7));
		accountHolder2.setPrimaryAddress(new Address("Calle Quim es un Crack", "08032", "Barcelona"));
		accountHolder2.setMailingAddress(new Address("Calle Barcelona", "08032", "Barcelona"));
		accountHolderRepository.save(accountHolder2);
		roleRepository.save(new Role("USER", accountHolder2));

		CheckingAccount checkingAccount = new CheckingAccount();
		checkingAccount.setPrimaryOwner(accountHolder2);
		checkingAccount.setBalance(new Money(BigDecimal.valueOf(1200)));
		checkingAccount.setCreationDate(LocalDate.now());
		checkingAccountRepository.save(checkingAccount);

		CheckingAccount checkingAccount2 = new CheckingAccount();
		checkingAccount2.setPrimaryOwner(accountHolder);
		checkingAccount2.setBalance(new Money(BigDecimal.valueOf(1000)));
		checkingAccount2.setCreationDate(LocalDate.now());
		checkingAccountRepository.save(checkingAccount2);

		Savings savings = new Savings();
		savings.setBalance(new Money(BigDecimal.valueOf(900)));
		savings.setInterestRate(BigDecimal.valueOf(0.0022));
		savings.setLastInterestDate(LocalDate.now());
		savings.setPrimaryOwner((accountHolder2));
		savings.setCreationDate(LocalDate.now());
		savings.setCreationDate(LocalDate.now());
		savingsRepository.save(savings);

		Savings savings2 = new Savings();
		savings2.setBalance(new Money(BigDecimal.valueOf(1100)));
		savings2.setInterestRate(BigDecimal.valueOf(0.0025));
		savings2.setLastInterestDate(LocalDate.now());
		savings2.setPrimaryOwner((accountHolder));
		savings2.setCreationDate(LocalDate.now());
		savings2.setCreationDate(LocalDate.now());
		savingsRepository.save(savings2);

	}
}
