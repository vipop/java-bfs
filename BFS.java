import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;

public class BFS
{
    // queue
    private static Queue<String> queue;
    // visited set
    private static HashSet<String> visited;
    // Adjacency List
    private static HashMap<String,HashSet<String>> aList;
    // array holding the labels of the vertices
    private static HashSet<String> vertices;

    public static void main(String[] args)
    {
        // INITIALIZE
        queue = new LinkedList<String>();
        visited = new HashSet<String>();
        aList = new HashMap<String,HashSet<String>>();
        vertices = new HashSet<String>();

        // READ INPUT FILE
        int input = readInput();
        if (input == 1)
        {
            System.out.println("");
            System.exit(0);
        }

        // TRAVERSE ALL GRAPHS
        for (String vertex : vertices)
        {
            if (!visited.contains(vertex))
            {
                traverse(vertex);
            }
        }

    }

    /**
    * Read the input file
    * return 0 if ok 1 if something went wrong
    **/
    public static int readInput()
    {

        // make a scanner for the input file
        Scanner scanner = new Scanner(System.in);
        // get number of vertices
        int numVerti = scanner.nextInt();
        if (numVerti == 0) return 1;
        // go to next line
        scanner.nextLine();
        // read the labels of the vertices
        for (int i = 0; i < numVerti; i++)
        {
            vertices.add(scanner.next());
        }
        scanner.nextLine();
        while (scanner.hasNextLine())
        {

            // save the input line
            String line = scanner.nextLine();
            // make a scanner over the saved input line
            Scanner lineScanner = new Scanner(line);
            // save the main vertex
            String vertex = lineScanner.next();
            // skip the "|"
            lineScanner.next();
            // make a set for all the vertices the main vertex is connected to
            HashSet<String> connected = new HashSet<String>();
            // fill out the set of vertices
            while(lineScanner.hasNext())
            {
                // save the vertex
                String verti = lineScanner.next();
                // add it to the set
                connected.add(verti);
            }
            // add the vertex and its set of connected vertices to the HashMap
            aList.put(vertex,connected);
        }

        return 0;
    }

    /**
    * Traverse a graph starting at a specific vertex
    * @param start the starting vertex
    **/
    public static void traverse(String start)
    {
        queue.add(start);
        // while the queue is not empty
        while(!queue.isEmpty())
        {
            // get the head of the queue
            String head = queue.remove();
            // if the head has not yet been visited
            if (!visited.contains(head))
            {
                // print the vertex
                System.out.println(head);
                // add it to the set of visited vertices
                visited.add(head);
                // retrieve its adjacent vertices
                HashSet<String> connected = aList.get(head);
                // find all adjacent vertex that has not been visited yet
                // and add them to the queue
                Iterator<String> iterator = connected.iterator();
                while (iterator.hasNext())
                {
                    // retrieve adjacent vertex
                    String adjV = iterator.next();
                    // if this vertex has not been visited before
                    if (!visited.contains(adjV) && !queue.contains(adjV))
                    {
                        // add it to the queue
                        queue.add(adjV);
                    }
                }
            }
        }
    }

}
