import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


class Teamwork{

    public static void main(String[] args) throws IOException {
        // print_trie_contributor(buildTrieContributors());
        print_trie_project(buildTrieProjects());
    }

    public static boolean isNumeric(String string) {
       return string.matches("[+-]?\\d*(\\.\\d+)?"); 
    }

    public static Node buildTrieContributors() throws IOException
    {
        String stringline;
        String[] stringsplit;

        int counter = -1;
        int numbercontributors = 0;

        File file = new File("b_better_start_small.in.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String firstline =  br.readLine();

        stringsplit = firstline.split(" ");

        numbercontributors = Integer.valueOf(stringsplit[0]);

        System.out.print(numbercontributors);

        boolean isfirstline = true;

        Node root_node = new Node(" ");
      
        Node current_node = root_node;
    
        while ((stringline = br.readLine()) != null && counter != numbercontributors)
        {

            stringsplit = stringline.split(" ");
            
            if(isfirstline)
            {
               
                counter++;

                String name = stringsplit[0];

                if(!current_node.child.containsKey(name))
                {
                    current_node.child.put(name,new Node(name));
                }

                current_node = current_node.child.get(name);
                
                isfirstline = false;
            }
            else
            {

                String skill = stringsplit[0];

                Integer skilllevel = Integer.valueOf(stringsplit[1]);

                current_node.skill = skill;

                current_node.skilllevel = skilllevel;

                isfirstline = true;

            } 
        }

        return root_node;

    } 

    public static NodeProject buildTrieProjects() throws IOException
    {
        String stringline;
        String[] stringsplit;

        int numbercontributors = 0;

        File file = new File("b_better_start_small.in.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String firstline =  br.readLine();

        stringsplit = firstline.split(" ");

        numbercontributors = Integer.valueOf(stringsplit[0]);

        System.out.print(numbercontributors);


        NodeProject root_node = new NodeProject(" ");
      
        NodeProject current_node = root_node;

        for(int i = 0 ;i < 100 ; i++)
        {
            br.readLine();
        }
    
        while ((stringline = br.readLine()) != null)
        {

            stringsplit = stringline.split(" ");
            
           
                String nameproject = stringsplit[0];

                Integer daysproject = Integer.valueOf(stringsplit[1]);

                Integer scoreproject = Integer.valueOf(stringsplit[2]);

                Integer rolesproject = Integer.valueOf(stringsplit[4]);

                if(!current_node.child.containsKey(nameproject))
                {
                    current_node.child.put(nameproject,new NodeProject(nameproject));
                    current_node.days = daysproject;
                    current_node.score = scoreproject;
                    current_node.roles = rolesproject;
               
                }

                

                for(int i = 0 ;i < rolesproject ;i++)
                {
                    stringsplit = br.readLine().split(" ");
                    String skill = stringsplit[0];
                    Integer level = Integer.valueOf(stringsplit[1]);
                    current_node.requairment.put(skill, level);
                }
            
            current_node = current_node.child.get(nameproject);
        }

        return root_node;

    } 

    public static void print_trie_contributor(Node trie)
    {
        if(trie.namecontributor != null)
        {
            System.out.println(trie.namecontributor+" "+trie.skill+" "+" "+trie.skilllevel);
        }
        for(String name :trie.child.keySet())
        {
            print_trie_contributor(trie.child.get(name));
        }
    }


    public static void print_trie_project(NodeProject trie)
    {
        if(trie.namecProject != null)
        {
            System.out.println(trie.namecProject+" "+trie.score+" "+" "+trie.requairment.keySet()+" "+trie.requairment.values());
        }
        for(String name :trie.child.keySet())
        {
            print_trie_project(trie.child.get(name));
        }
    }

}

class NodeProject
{
    String namecProject;
    Integer days;
    Integer score;
    Integer roles;
    HashMap<String ,NodeProject> child;
    HashMap<String ,Integer> requairment;
    public NodeProject(String namecProject)
    {
     this.namecProject = namecProject;
     this.score = null;
     this.days = null;
     this.roles = null;
     child = new HashMap<>();
     requairment = new HashMap<>();
    }
}
class Node{
    String namecontributor;
    Boolean isinwork;
    HashMap<String ,Node> child;
    String skill;
    Integer skilllevel;
    public Node(String namecontributor)
    {
     this.namecontributor = namecontributor;
     this.isinwork = false;
     this.skill = null;
     this.skilllevel = null;
     child = new HashMap<>();
    }
}