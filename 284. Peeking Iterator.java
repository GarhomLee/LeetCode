https://leetcode.com/problems/peeking-iterator/

// 思路：内部维护两个变量：
//      1）peekingIterator，指向传入参数iterator
//      2）peek，作为iterator.next()的缓存，初始值和如果iterator没有next时为null
//      实现以下功能：
//      1）peek()，直接返回peek
//      2）next()，因为peek已经是iterator.next()的缓存，所以用临时变量next存peek，最后返回next。
//         同时，需要更新peek为下一个iterator.next()。如果iterator没有next，赋值为null。
//      3）hasNext()，返回peek是否为null，如果不为null说明iterator.next()存在

class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> peekingIterator;
    Integer peek;
    
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    peekingIterator = iterator;
        if (peekingIterator.hasNext()) {
            peek = peekingIterator.next();
        }
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return peek;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    Integer next = peek;
        peek = peekingIterator.hasNext() ? peekingIterator.next() : null;
        return next;
	}

	@Override
	public boolean hasNext() {
	    return peek != null;
	}
}