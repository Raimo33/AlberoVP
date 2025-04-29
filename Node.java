/*================================================================================

File: Node.java                                                                 
Creator: Claudio Raimondi                                                       
Matricola: 7158162                                                              

created at: 2025-04-25 15:43:23                                                 
last edited: 2025-04-25 15:43:23                                                

================================================================================*/

public class Node<T>
{
  public Node(T data)
  {
    this.data = data;
  }

  // O(1)
  public T getData()
  {
    return data;
  }

  // O(1)
  public void setData(T data)
  {
    this.data = data;
  }

  @Override
  public boolean equals(Object obj)
  {
    return this == obj;
  }

  @Override
  public int hashCode()
  {
    return System.identityHashCode(this);
  }

  private T data;
}