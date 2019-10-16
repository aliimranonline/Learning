package java8.learning.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class StreamsExcersies {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		List<Transaction> transactions = Arrays.asList(
		new Transaction(brian, 2011, 300),
		new Transaction(raoul, 2012, 1000),
		new Transaction(raoul, 2011, 400),
		new Transaction(mario, 2012, 710),
		new Transaction(mario, 2012, 700),
		new Transaction(alan, 2012, 950)
		);
		
		//Q1:Find all transactions in the year 2011 and sort them by value (small to high).
		List<Transaction> lstTransactions=transactions.stream().filter(i -> i.getYear()==2011).sorted(java.util.Comparator.comparing(Transaction::getValue)).collect(toList());
		lstTransactions.size();
		
		//Q2:What are all the unique cities where the traders work?
		Object[]arr=transactions.stream().map(i -> i.getTrader().getCity()).distinct().toArray();
		int length=arr.length;
		
		//Q3:Find all traders from Cambridge and sort them by name.
		List<Trader> cambridgeTraders=transactions.stream().filter(i -> i.getTrader().getCity().equalsIgnoreCase("Cambridge")).map(Transaction::getTrader).sorted(java.util.Comparator.comparing(Trader::getName)).collect(toList());
		cambridgeTraders.size();
		
		//Q4:Return a string of all trader's names sorted alphabetically
		String csvNames=transactions.stream().map(i ->i.getTrader().getName()).distinct().sorted().reduce("", (a,b)->a+" "+b);
		System.out.println("CSV Names: "+csvNames);
		
		//Q5:Are any traders based in Milan?
		boolean anyMilanTrader=transactions.stream().anyMatch(i -> i.getTrader().getCity().equalsIgnoreCase("Milan"));
		
		//Q6:Print the values of all transactions from the traders living in Cambridge
		
		transactions.stream().filter(i -> i.getTrader().getCity().equalsIgnoreCase("Cambridge")).forEach(i->System.out.println(i.getValue()));
		
		//OR Using Map
		
		transactions.stream()
		.filter(t -> "Cambridge".equals(t.getTrader().getCity()))
		.map(Transaction::getValue)
		.forEach(System.out::println);
		
		//Q7:What’s the highest value of all the transactions?
		Optional<Integer> optionalMax=transactions.stream().map(Transaction::getValue).reduce(Integer::max);
		System.out.println("Max INT: "+optionalMax.get());
		
		//Q8:Find the transaction with the smallest value
		Optional<Transaction> minTrans1=transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
		System.out.println("Minimum Transaction 1:"+minTrans1.get().getValue());
		//OR
		Optional<Transaction> minTrans2=transactions.stream().min(java.util.Comparator.comparing(Transaction::getValue));
		System.out.println("Minimum Transaction 2:"+minTrans2.get().getValue());
		
		
		
	}

}
