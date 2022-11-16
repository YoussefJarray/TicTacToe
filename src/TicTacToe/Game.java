package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	static boolean playing = true;

	public static void main(String[] args) {
		
		
		char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}};
				
		while(playing==true){
			Scanner scan = new Scanner(System.in);

			//detect user input
			System.out.println("Donner une placement: ");
			int playerPos = scan.nextInt();
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("Position taken! Enter a correct position");
				playerPos = scan.nextInt();
			}
			placePiece(gameBoard, playerPos, "player");
			
			//generating cpu response using random
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				System.out.println("Thinking");
				cpuPos = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, cpuPos, "cpu");
			
			//printing board
			printGameBoard(gameBoard);
			
			String result = checkWin();
			System.out.println(result);
		}
		
	}
	
	public static void printGameBoard(char[][] gameBoard) {
		for(char[] row : gameBoard) {
			for(char line : row) {
				System.out.print(line);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		char Symbol = ' ';
		
		if(user.equals("player")) {
			Symbol = 'X';
			playerPositions.add(pos);
		}else if(user.equals("cpu")) {
			Symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
			case 1:
				gameBoard[0][0] = Symbol;
				break;
			case 2:
				gameBoard[0][2] = Symbol;
				break;
			case 3:
				gameBoard[0][4] = Symbol;
				break;
			case 4:
				gameBoard[2][0] = Symbol;
				break;
			case 5:
				gameBoard[2][2] = Symbol;
				break;
			case 6:
				gameBoard[2][4] = Symbol;
				break;
			case 7:
				gameBoard[4][0] = Symbol;
				break;
			case 8:
				gameBoard[4][2] = Symbol;
				break;
			case 9:
				gameBoard[4][4] = Symbol;
				break;
			default:
				break;
		}
	}
	
	public static String checkWin() {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(7, 5, 3);
		
		List<List> winning = new ArrayList<>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for(List l : winning) {
			if(playerPositions.containsAll(l)) {
				playing = false;
				return "Congrats, you won!";
			}else if(cpuPositions.containsAll(l)) {
				playing = false;
				return "Computer wins! :(";
			}else if(playerPositions.size() + cpuPositions.size()==9) {
				playing = false;
				return "TIE!";
			}
		}
		return "";
	}

}
