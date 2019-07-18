package pl.networks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private String[] movieList(String filePath)
    {
        File file = new File(filePath);
        Scanner scanner;
        String MovieList[] = new String[25];
        try
        {
            scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine())
            {
                MovieList[i++] = scanner.nextLine();
            }
            return MovieList;
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return null;
    }

    private char[] pickAMovie(String[] movieList)
    {
        Random rand = new Random();
        String movie = movieList[rand.nextInt(movieList.length) - 1];
        char chosenMovie[] = movie.toCharArray();
        return chosenMovie;
    }

    private boolean checkAnswer(char answer, char[] movieName)
    {
        for (char letter: movieName)
        {
            if (answer == letter)
            {
                return true;
            }
        }
        return false;
    }
    private boolean checkWin(char movieName[])
    {
        for (char letter: movieName)
        {
            if (letter == '_')
            {
                return false;
            }
        }
        return true;
    }

    public void startGame()
    {
        //chose movie to guess
        char chosenMovie[] = pickAMovie(movieList("C:\\Users\\k.starzyk\\Java-practice\\java-OOP-2\\MovieList.txt"));
        char correctLetters[] = new char[chosenMovie.length];
        char wrongLetters[] = new char[50];
        char movieName[] = new char[chosenMovie.length];
        String playerLetter = "";
        Scanner scanner = new Scanner(System.in);
        int chances = 10;
        int correctLettersCell = 0;
        int wrongLettersCell = 0;
        char answer;


        System.out.println("Try to guess this movie:");

        System.out.println(movieName);

        //Game starts
        while (chances > 0)
        {
            System.out.println("You have " + chances +" left.");
            //Change movie name so only see letters player guess right
            int i = 0;
            for (char letter : chosenMovie)
            {
                if (letter == ' ')
                {
                    movieName[i] = ' ' ;
                }
                else if (checkAnswer(letter, correctLetters))
                {
                    movieName[i] = letter;
                }
                else
                {
                    movieName[i] = '_';
                }
                i++;
            }
            //Print out movie name for player
            for (char letter: movieName)
            {
                System.out.print(letter);
            }

            //Check if player won already
            if (checkWin(movieName))
            {
                System.out.println("You Won!!");
                break;
            }

            //Letters that player chosen wrong
            System.out.println("\nLetters you already used: ");

            for (char letter: wrongLetters)
            {
                System.out.print(letter);
            }


            //Take new letter from player
            System.out.println("\nWrite a letter: ");

            playerLetter = scanner.nextLine().toLowerCase();
            if (!playerLetter.isEmpty())
            {
                answer = playerLetter.toCharArray()[0];
            }
            else
            {
                System.out.println("Please write a letter.");
                answer = ' ';
            }

            //Check if input is a letter
            if(Character.isLetter(answer))
            {
                //check if letter was already used
                if (!checkAnswer(answer, correctLetters) && !checkAnswer(answer, wrongLetters))
                {
                    //check if letter is correct
                    if (checkAnswer(answer, chosenMovie))
                    {
                        System.out.println("Correct letter!");
                        correctLetters[correctLettersCell++] = answer;
                    }
                    //Wrong guess -1 chances
                    else
                    {
                        chances--;
                        System.out.println("Wrong letter");
                        wrongLetters[wrongLettersCell++] = answer;
                    }
                }
                //Player used already a letter
                else
                {
                    System.out.println("You used this letter already");
                }

            }
            else
            {
                System.out.println("This is not a letter, try again.");
            }


        }

        System.out.println("You loose to bad, the answer was: ");
        for (char letter: chosenMovie)
        {
            System.out.print(letter);
        }
    }
}
