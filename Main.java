/*================================================================================

File: Main.java                                                                 
Creator: Claudio Raimondi                                                       
Email: claudio.raimondi@pm.me                                                   

created at: 2025-04-30 19:06:03                                                 
last edited: 2025-04-30 19:06:03                                                

================================================================================*/


import java.util.List;
import java.util.ArrayList;

public class Main
{
  public static void main(String[] args)
  {
    run();
  }

  public static void run()
  {
    Tree<String> tree = new Tree<>();

    Node<String> root = new Node<>("Root");
    tree.setRoot(root);
    printTree(tree);

    Node<String> actual_root = new Node<>("Actual Root");
    tree.updateRoot(actual_root);
    printTree(tree);

    Node<String> child1 = new Node<>("Child 1");
    tree.insertNode(root, child1);
    printTree(tree);

    Node<String> child2 = new Node<>("Child 2");
    tree.insertNode(root, child2);
    printTree(tree);

    Node<String> grandchild1_1 = new Node<>("Grandchild 1_1");
    tree.insertNode(child1, grandchild1_1);
    printTree(tree);

    Node<String> grandchild1_2 = new Node<>("Grandchild 1_2");
    tree.insertNode(child1, grandchild1_2);
    printTree(tree);

    Node<String> grandchild2_1 = new Node<>("Grandchild 2_1");
    tree.insertNode(child2, grandchild2_1);
    printTree(tree);

    Node<String> grandchild2_2 = new Node<>("Grandchild 2_2");
    tree.insertNode(child2, grandchild2_2);
    printTree(tree);

    Node<String> grandchild2_3 = new Node<>("Grandchild 2_3");
    tree.insertNode(child2, grandchild2_3);
    printTree(tree);

    System.out.println("height: " + tree.getHeight());

    {
      List<Node<String>> bfs = tree.traverseBreadthFirst();
      System.out.println("BFS:");
      printNodes(bfs);
    }
    
    {
      List<Node<String>> dfs = tree.traverseDepthFirst();
      System.out.println("DFS:");
      printNodes(dfs);
    }
  }

  private static <T> void printNodes(List<Node<T>> list)
  {
    for (Node<T> node : list)
      System.out.print(node.getData() + ", ");
    System.out.println();
  }

  public static <T> void printTree(Tree<T> tree)
  {
    printSubtree(tree, tree.getRoot(), 0);
    System.out.println();
  }
  
  private static <T> void printSubtree(Tree<T> tree, Node<T> node, int depth)
  {
    if (node == null)
      return;

    for (int i = 0; i < depth; i++)
      System.out.print("  ");

    System.out.printf("%s | Depth: %d | Children: %d | Leaves: %d | Parent: %s%n",
      node.getData(),
      tree.getDepth(node),
      tree.getChildrenCount(node),
      tree.getLeavesCount(node),
      tree.getParent(node) != null ? tree.getParent(node).getData() : "null");

    for (Node<T> child : tree.getChildren(node))
      printSubtree(tree, child, depth + 1);
  }
}