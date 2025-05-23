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
    cached_height = -1;
    cached_depths = new HashMap<>();
    cached_children = new HashMap<>();
  }

  // O(1)
  public Node<T> setRoot(Node<T> root) throws IllegalStateException, IllegalArgumentException
  {
    if (nodes.isEmpty() == false)
      throw new IllegalStateException("Root already set");
    if (root == null)
      throw new IllegalArgumentException("Root cannot be null");

    nodes.add(root);
    parents.add(null);
    cached_height = 0;

    return root;
  }

  // O(1)
  public Node<T> updateRoot(Node<T> root) throws IllegalStateException, IllegalArgumentException
  {
    if (nodes.isEmpty())
      throw new IllegalStateException("Root not set");
    if (root == null)
      throw new IllegalArgumentException("Root cannot be null");

    nodes.add(0, root);
    parents.add(0, null);
    parents.set(1, root);
    cached_height++;

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

  // O(1) with memoization
  public List<Node<T>> getChildren(Node<T> node) throws IllegalArgumentException
  {
    if (node == null)
      throw new IllegalArgumentException("Node cannot be null");
    if (nodes.indexOf(node) == -1)
      throw new IllegalArgumentException("Node not found in tree");

    if (cached_children.containsKey(node))
      return cached_children.get(node);

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

    cached_children.put(node, children);
    return children;
  }

  // O(n)
  public Node<T> getParent(Node<T> node) throws IllegalArgumentException
  {
    if (node == null)
      throw new IllegalArgumentException("Node cannot be null");

    final int index = nodes.indexOf(node);
    if (index == -1)
      throw new IllegalArgumentException("Node not found in tree");

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
  public int getDepth(Node<T> node) throws IllegalArgumentException
  {
    if (node == null)
      throw new IllegalArgumentException("Node cannot be null");

    if (cached_depths.containsKey(node))
      return cached_depths.get(node);

    int depth = 0;
    Node<T> parent = getParent(node);

    while (parent != null)
    {
      depth++;
      parent = getParent(parent);
    }

    cached_depths.put(node, depth);
    return depth;
  }

  // O(1)
  public Node<T> insertNode(Node<T> parent, Node<T> node) throws IllegalArgumentException
  {
    if (node == null)
      throw new IllegalArgumentException("Node cannot be null");

    nodes.add(node);
    parents.add(parent);

    clearCaches();

    return node;
  }

  // O(1) with memoization
  public int getHeight()
  {
    if (cached_height != -1)
      return cached_height;

    int height = 0;
    for (Node<T> node : nodes)
      height = Math.max(height, getDepth(node));

    cached_height = height;
    return height;
  }

  // O(1) with memoization
  public int getChildrenCount(Node<T> node)
  {
    if (node == null)
      throw new IllegalArgumentException("Node cannot be null");

    List<Node<T>> children = getChildren(node);
    return children.size();
  }

  // O(n) with memoization
  public int getLeavesCount(Node<T> node) throws IllegalArgumentException
  {
    if (node == null)
      throw new IllegalArgumentException("Node cannot be null");

    List<Node<T>> children = getChildren(node);

    if (children.isEmpty())
      return 1;
    
    int leaves_count = 0;
    for (Node<T> child : children)
      leaves_count += getLeavesCount(child);

    return leaves_count;
  }

  // O(n) with memoization
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

  // O(n) with memoization
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
    cached_depths.clear();
    cached_children.clear();
    cached_height = -1;
  }

  private List<Node<T>> nodes;
  private List<Node<T>> parents;

  private int cached_height;
  private Map<Node<T>, Integer> cached_depths;
  private Map<Node<T>, List<Node<T>>> cached_children;
}
