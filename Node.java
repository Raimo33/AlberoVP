/*================================================================================

File: Node.java                                                                 
Creator: Claudio Raimondi                                                       
Matricola: 7158162                                                              

created at: 2025-04-25 15:43:23                                                 
last edited: 2025-04-25 15:43:23                                                

================================================================================*/

/*================================================================================

File: Node.java                                                               
Creator: Claudio Raimondi                                                       
Matricola: 7158162                                                              

created at: 2025-04-25 13:58:09                                                 
last edited: 2025-04-25 13:58:09                                                

================================================================================*/

public class Node<T>
{
  public Node(T data)
  {
    this.data = data;
  }

  // O(1)
  public Node<T> getData()
  {
    return data;
  }

  // O(1)
  public void setData(T data)
  {
    this.data = data;
  }

  private T data;
}