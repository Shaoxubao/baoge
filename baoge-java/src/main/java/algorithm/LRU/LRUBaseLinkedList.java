package algorithm.LRU;

/**
 * @Author shaoxubao
 * @Date 2019/8/8 15:56
 *
 * 基于单链表LRU算法
 *
 */
public class LRUBaseLinkedList<T> {
    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 3;

    /**
     * 头结点
     */
    private SNode<T> headNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);

        // 链表中存在，删除原数据，再插入到链表的头部
        if (preNode != null) {
            deleteElemOptim(preNode);
            insertElementBegin(data);
        } else {
            if (length >= this.capacity) {
                //删除尾结点
                deleteElemAtEnd();
            }
            insertElementBegin(data);
        }
    }

    /**
     * 删除preNode结点下一个元素
     *
     * @param preNode
     */
    private void deleteElemOptim(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 链表头部插入节点
     *
     * @param data
     */
    private void insertElementBegin(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        length++;
    }

    /**
     * 获取查找到元素的前一个结点
     *
     * @param data
     * @return
     */
    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 获取查找到元素的后一个结点
     *
     * @param data
     * @return
     */
    private SNode findNextNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node.getNext().getNext();
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 获取查找到元素结点
     *
     * @param data
     * @return
     */
    private SNode findCurrentNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node.getNext();
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 查找元素(并将查找元素移向头部)
     */
    public T findNode(T data) {
        SNode currentNode = findCurrentNode(data);
        if (currentNode == null) {
            return null;
        }

        // 删除当前节点
        removeNode(currentNode);

        // 将当前节点移向头部
        moveToHead(currentNode);

        return (T) currentNode.element;
    }

    private void moveToHead(SNode node) {
        node.next = headNode.next;
        headNode.next = node;
    }

    private void removeNode(SNode node) {
        T data = (T) node.element;

        SNode preNode = findPreNode(data);
        SNode nextNode = findNextNode(data);

        preNode.next = nextNode;
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        // 空链表直接返回
        if (ptr.getNext() == null) {
            return;
        }

        // 倒数第二个结点
        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }

        SNode tmp = ptr.getNext();
        ptr.setNext(null);
        tmp = null;
        length--;
    }

    public void printAll() {
        SNode node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }


    public class SNode<T> {

        private T element;

        private SNode next;

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

}
