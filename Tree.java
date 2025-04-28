/*================================================================================

File: Tree.java                                                                 
Creator: Claudio Raimondi                                                       
Matricola: 7158162                                                              

created at: 2025-04-25 15:43:23                                                 
last edited: 2025-04-25 15:43:23                                                

================================================================================*/

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;

public class Tree<T>
{
  public Tree()
  {
    nodes = new ArrayList<>();
    parents = new ArrayList<>();
    height = 0;
    depth_cache = new HashMap<>();
  }

  // O(1)
  public Node<T> setRoot(T data) throws IllegalStateException
  {
    if (nodes.isEmpty() == false)
      throw new IllegalStateException("Root already set");

    Node<T> root = new Node<>(data);
    nodes.add(root);
    parents.add(null);
    height = 0;

    return root;
  }

  // O(1)
  public Node<T> updateRoot(T data)
  {
    if (nodes.isEmpty())
      return setRoot(data);

    Node<T> root = new Node<>(data);
    nodes.add(0, root);
    parents.add(0, null);
    parents.set(1, root);
    height++;

    clearCaches();

    return root;
  }

  // O(1)
  public Node<T> getRoot()
  {
    if (nodes.isEmpty())
      return null;

    return nodes.get(0);
  }

  // O(n)
  public List<Node<T>> getChildren(Node<T> node)
  {
    List<Node<T>> children = new ArrayList<>();

    ListIterator<Node<T>> nodesIter = nodes.listIterator();
    ListIterator<Node<T>> parentsIter = parents.listIterator();

    while (nodesIter.hasNext())
    {
      Node<T> currentNode = nodesIter.next();
      Node<T> currentParent = parentsIter.next();

      if (currentParent == node)
        children.add(currentNode);
    }

    return children;
  }

  // O(n)
  public Node<T> getParent(Node<T> node)
  {
    final int index = nodes.indexOf(node);

    if (index == -1)
      return null;

    return parents.get(index);
  }

  // O(1)
  public int getNodeCount()
  {
    return nodes.size();
  }

  // O(1) with memoization
  public int getDepth(Node<T> node)
  {
    if (depth_cache.containsKey(node))
      return depth_cache.get(node);

    int depth = 0;
    Node<T> parent = getParent(node);

    while (parent != null)
    {
      depth++;
      parent = getParent(parent);
    }

    depth_cache.put(node, depth);
    return depth;
  }

  // O(1)
  public Node<T> insertNode(Node<T> parent, T data)
  {
    Node<T> node = new Node<>(data);
    nodes.add(node);
    parents.add(parent);

    clearCaches();

    return node;
  }

  // O(1)
  public int getHeight()
  {
    return height;
  }

  // O(n)
  public int getChildrenCount(Node<T> node)
  {
    List<Node<T>> children = getChildren(node);
    return children.size();
  }

  // O(n^2)
  public int getLeavesCount(Node<T> node)
  {
    List<Node<T>> children = getChildren(node);

    if (children.isEmpty())
      return 1;
    
    int leaves_count = 0;
    for (Node<T> child : children)
      leaves_count += getLeavesCount(child);

    return leaves_count;
  }

  // O(n^2)
  public List<Node<T>> traverseBreadthFirst()
  {
    List<Node<T>> nodes = new ArrayList<>();
    Queue<Node<T>> queue = new LinkedList<>();

    queue.add(getRoot());

    while (!queue.isEmpty())
    {
      Node<T> current = queue.poll();
      nodes.add(current);

      List<Node<T>> children = getChildren(current);
      for (Node<T> child : children)
        queue.add(child);
    }

    return nodes;
  }

  // O(n^2)
  public List<Node<T>> traverseDepthFirst()
  {
    List<Node<T>> nodes = new ArrayList<>();
    preOrderTraversal(getRoot(), nodes);
    return nodes;
  }

  private void preOrderTraversal(Node<T> node, List<Node<T>> nodes)
  {
    if (node == null)
      return;

    nodes.add(node);

    List<Node<T>> children = getChildren(node);
    for (Node<T> child : children)
      preOrderTraversal(child, nodes);
  }

  private void clearCaches()
  {
    depth_cache.clear();
  }

  private List<Node<T>> nodes;
  private List<Node<T>> parents;
  private int height;
  private Map<Node<T>, Integer> depth_cache;
  //TODO children_cache
}
