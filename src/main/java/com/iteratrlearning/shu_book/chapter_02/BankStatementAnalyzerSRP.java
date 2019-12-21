package com.iteratrlearning.shu_book.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementAnalyzerSRP {

    private static final String RESOURCES = "src/test/resources/";

    public static void main(String[] args) throws Exception {

        BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

//        Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        Path path = Paths.get(RESOURCES + args[0]);
        List<String> lines = Files.readAllLines(path);

        List<BankStatement> bankStatements = bankStatementParser.parseLinesFrom(lines);

        System.out.println("The total for all transactions is " + calculateTotalAmount(bankStatements));
        System.out.println("Transactions in January " + selectInMonth(bankStatements, Month.JANUARY));
    }

    private static double calculateTotalAmount(List<BankStatement> bankStatements) {
        return bankStatements.stream().mapToDouble(BankStatement::getAmount).sum();
    }

    private static List<BankStatement> selectInMonth(List<BankStatement> bankStatements, Month month) {
        return bankStatements.stream()
                .filter(bankStatement -> month.equals(bankStatement.getDate().getMonth()))
                .collect(Collectors.toList());
    }
}