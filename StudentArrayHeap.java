package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {
  public StudentArrayHeap(Comparator<P> comparator) {
    super(comparator);
  }

  protected int getLeftChildOf(int index) {
	if (index < 0) {
	  throw new IndexOutOfBoundsException();
	}
	int leftChildIndex = (2 * index + 1);
	return leftChildIndex;
  }

  protected int getRightChildOf(int index) {
    if (index < 0) {
	  throw new IndexOutOfBoundsException();
    }
    int rightChildIndex = (2 * index + 2);
    return rightChildIndex;
  }

  protected int getParentOf(int index) {
    if (index < 1) {
	  throw new IndexOutOfBoundsException();
    }
    int parentIndex = (index - 1) / 2;
    return parentIndex;
  }

  protected void bubbleUp(int index) {
	if (index == 0) {
	  return;
	}
	  
	Entry<P, V> entry = heap.get(index);
	P elemPriority = entry.getPriority();
	
	int parentIndex = this.getParentOf(index);
	Entry<P, V> parentEntry = heap.get(parentIndex);
	P parentPriority = parentEntry.getPriority();
	
	while ((index > 0) && comparator.compare(elemPriority, parentPriority) > 0) {
	  this.swap(index, parentIndex);
	  index = parentIndex;
	  parentIndex = (parentIndex - 1) / 2;
	  elemPriority = heap.get(index).getPriority();
	  parentPriority = heap.get(parentIndex).getPriority();
	}
	
  }

  protected void bubbleDown(int index) {
    int largerChildIndex;
    Entry<P, V> entry = heap.get(index);
	P entryPriority = entry.getPriority();
	while (index < this.size() / 2) {
	  int leftIndex = this.getLeftChildOf(index);
	  P leftPriority = heap.get(leftIndex).getPriority();
	  int rightIndex = this.getRightChildOf(index);
	  if(rightIndex < this.size()) {
		P rightPriority = heap.get(rightIndex).getPriority();
		if (rightIndex < this.size() && comparator.compare(leftPriority, rightPriority) < 0) {
		  largerChildIndex = rightIndex;
		}
		else {
		  largerChildIndex = leftIndex;
		}
	  }
	  else {
		largerChildIndex = leftIndex;	  
	  }
	  
	  P largerChildPriority = heap.get(largerChildIndex).getPriority();
	  if (comparator.compare(entryPriority, largerChildPriority) >= 0) {
		break;
	  }
	  
	  this.swap(largerChildIndex, index);
	  index = largerChildIndex;
	}
  }
}

