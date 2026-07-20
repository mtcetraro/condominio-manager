package it.mtcetraro.condominio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        System.out.println("Benvenuto su CondominioApp\nConnessione al database\n");
        Home home = new Home();
        home.Accesso();
    }
}