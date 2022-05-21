package br.com.up;

import java.sql.*;
import java.util.*;

public class Principal 
{
	
	public static void lineTableInter(String tm, String bord)
	{
		for(int i = 0; i < (tm.length() + 3); i++)
		{
			if(i == 0)
			{
				System.out.format("%s", bord.charAt(0));
			}
			else if(i > 0)
			{
				System.out.format("─");
			}
			if(i == (tm.length() + 2))
			{
				System.out.format("%s",bord.charAt(1));
			}
		}
		
	}
	
	public static void table(String txt)
	{
		String bordSup = "╭╮";
		String bordSub = "╰╯";
		
		lineTableInter(txt, bordSup);
		
		System.out.println();
		
		System.out.format("│ %s │", txt);
		
		System.out.println();
		
		lineTableInter(txt, bordSub);
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String ... $) 
	{
		
		String URL = "jdbc:sqlite:C:\\Users\\Lugaw\\Desktop\\bancoProjeto.db";
		
		/*
		try
		{
			
			Connection con = DriverManager.getConnection(URL);
			
			Statement stmt = con.createStatement();
			
			
			
		}catch  (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		Scanner leia = new Scanner(System.in);
		
		String txt;
		
		int login;
		
		String nomeLogin;
		String senhaLogin;
		String nomeBanco;
		String senhaBanco;
		String nomeCadastro;
		String senhaCadastro;
		
		boolean acesso = null != null;
		
		try (
				Connection con = DriverManager.getConnection(URL);
				
				Statement stmt = con.createStatement();
				)
		{
			
			
		
		System.out.format("HORÁRIO DE ÔNIBUS\n\nPossui login?\n(sim = 1/nao = 2)\n");
		login = leia.nextInt();
		
		if(login == 1)
		{
			
			System.out.format("LOGIN\n\n");
			
			leia.nextLine();
			
			System.out.format("Digite seu nome:\n");
			nomeLogin = leia.nextLine();
			
			System.out.format("Digite sua senha:\n");
			senhaLogin = leia.nextLine();
			
				String querySelect = "select * from usuarios;";
				Statement stmtSelect = con.createStatement();
				stmtSelect.execute(querySelect);
				
				
				ResultSet resultado = stmt.executeQuery("select * from usuarios");
				
				while(resultado.next())
				{
					nomeBanco = resultado.getString(2);
					senhaBanco = resultado.getString(3);
					
					int compare = nomeBanco.compareTo(nomeLogin) + senhaBanco.compareTo(senhaLogin);
					
					if(compare == 0)
					{
						acesso = true;
						System.out.println("deu");
					}
					else
					{
						acesso = false;
					}
				}
			
		}
		else
		{
			System.out.format("CADASTRO\n\n");
			
			leia.nextLine();
			
			System.out.println("Digite seu nome:\n");
			nomeCadastro = leia.nextLine();
			
			System.out.println("Digite sua senha:\n");
			senhaCadastro = leia.nextLine();

				String queryInsert = "INSERT INTO usuarios (nome,senha) VALUES('" + nomeCadastro + "', '" + senhaCadastro + "');";
				Statement stmtInsert = con.createStatement();
				stmtInsert.execute(queryInsert);
		}

		
		System.out.println(acesso);
		
		System.out.println("Digite o nome de usuário:");
		txt = leia.nextLine();
		
		leia.close();
		
		table(txt);
		
		con.close();
		}catch  (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
