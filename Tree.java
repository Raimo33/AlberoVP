/*================================================================================

File: Tree.java                                                                 
Creator: Claudio Raimondi                                                       
Matricola: 7158162                                                              

created at: 2025-04-25 15:43:23                                                 
last edited: 2025-04-25 15:43:23                                                

================================================================================*/

import java.util.ArrayList;
import java.util.List;

public class Tree<T>
{
  public Tree()
  {
    nodes = new ArrayList<>();
    parents = new ArrayList<>();
  }

  public Node<T> setRoot(T data) throws IllegalStateException
  {
    if (nodes.isEmpty() == false)
      throw new IllegalStateException("Root already set");

    Node<T> radice = new Node<>(data);
    nodes.add(radice);
    parents.add(null);
    return radice;
  }

  public Node<T> updateRoot(T data)
  {
    if (nodes.isEmpty())
      return setRoot(data);

    Node<T> radice = new Node<>(data);
    nodes.add(0, radice);
    parents.add(0, null);
    parents.set(1, radice);
    return radice;
  }

  public Node<T> getRoot()
  {
    return nodes.isEmpty() ? null : nodes.get(0);
  }

  public List<Node<T>> getChildren(Node<T> node)
  {
    List<Node<T>> children = new ArrayList<>();

    final int size = nodes.size();
    for (int i = 0; i < size; i++)
    {
      if (parents.get(i) == node)
        children.add(nodes.get(i));
    }

    return children;
  }

  public Node<T> getParent(Node<T> node)
  {
    final int index = nodes.indexOf(node);
    return index == -1 ? null : parents.get(index);
  }

  public int getNodeCount()
  {
    return nodes.size();
  }

  public int getDepth(Node<T> node)
  {
    int depth = 0;

    int index = nodes.indexOf(node);
    Node<T> parent = parents.get(index);

    while (parent != null)
    {
      depth++;
      index = nodes.indexOf(parent);
      parent = parents.get(index);
    }

    return depth;
  }

  public Node<T> insertNode(Node<T> parent, T data)
  {
    Node<T> node = new Node<>(data);
    nodes.add(node);
    parents.add(parent);

    return node;
  }

  public int getChildrenCount(Node<T> node)
  {
    int count = 0;

    final int size = nodes.size();
    for (int i = 0; i < size; i++)
    {
      if (parents.get(i) == node)
        count++;
    }

    return count;
  }

  // public int getHeight(Node<T> node)
  // {
  //   int height = 0;

  //   List<Node<T>> children = getChildren(node);
  //   for (Node<T> child : children)
  //   {
  //     int childHeight = getHeight(child);
  //     height = Math.max(height, childHeight);
  //   }

  //   return height + 1;
  // }

  private List<Node<T>> nodes;
  private List<Node<T>> parents;
}
