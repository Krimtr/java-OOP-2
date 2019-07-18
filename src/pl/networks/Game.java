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
        char wrongLetters[] = new char[chosenMovie.length *2];
        char movieName[] = new char[chosenMovie.length];

        Scanner scanner = new Scanner(System.in);
        int chances = 10;
        int correctLettersCell = 0;
        int wrongLettersCell = 0;
        char answer;


        System.out.println("Try to guess this movie:");

        System.out.println(movieName);


        while (chances > 0)
        {
            System.out.println("You have " + chances +" left.");
            //Change movie name
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

            for (char letter: movieName)
            {
                System.out.print(letter);
            }

            System.out.println("\nLetters you already used: ");

            for (char letter: wrongLetters)
            {
                System.out.print(letter);
            }

            if (checkWin(movieName))
            {
                System.out.println("You Won!!");
                break;
            }
            
            System.out.println("\nWrite a letter: ");

            answer = scanner.nextLine().toCharArray()[0];



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
                    else
                    {
                        chances--;
                        System.out.println("Wrong letter");
                        wrongLetters[wrongLettersCell++] = answer;
                    }
                }
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

    }
}
