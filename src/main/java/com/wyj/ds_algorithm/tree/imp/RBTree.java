package com.wyj.ds_algorithm.tree.imp;

/**
 * <b>红黑树</b>
 * <p>一： 红黑树的特点
 * <li>节点 非红即黑</li>
 * <li>根节点为黑色节点</li>
 * <li>每个(为null的)叶子节点是黑色(ps:所有的叶子节点都是null)</li>
 * <li>红色节点的子节点必为黑色节点</li>
 * <li>任何节点到其所有子节点的黑色节点的个数相同</li>
 *
 * <br><br>若满足了上述所有条件，那么这个二叉排序树就是红黑树。
 * <br><b>RBTree红黑树</b>也是一颗较为平衡的二叉排序树，较<b>AVLTree二叉平衡树</b>而言，
 * 插入操作的旋转次数要小的多，AVLTree适合查询多，插入删除操作较少的情况。RBTree相当于是对插入删除
 * 操作的优化，降低了旋转的次数，提高了效率。
 * </p><br><br>
 * 
 * 
 * <p>二：插入操作
 * <p>首先，插入操作就像是插入到普通二叉排序树种一样，但是该节点标为红色</p>
 * <p>然后，检查树，保持是一颗红黑树</p>
 * <ol>插入节点是根节点，直接变黑，完事。</ol>
 * <ol>插入节点是子节点，父亲黑色的，完事。</ol>
 * <ol>插入节点是子节点，父亲是红色，叔叔是黑色。父亲变黑，爷爷变红，以爷爷为中心 向右旋转，完事。如下面所示：<pre>
 *      黑
 *     / \
 *    红       黑
 *   /
 *  新红
 * </pre>如果`新红节点`是右子节点，那么需要先按照父亲节点向左旋转，变成上面说的样子。如果父亲节点在右边也是类似的。
 * </ol>
 * <ol>插入节点是子节点，父亲是红色，叔叔也是红色。父亲变黑，叔叔变黑，爷爷变红，再以爷爷为新插入的节点来平衡。</ol>
 * <p>通过上面的检查这棵树一定还是一颗红黑树。</p>
 * 在我学习的整个过程中发现，其实最重要的是维护两点<b>任何节点到其所有子节点的黑色节点的个数相同</b>和<b>红色节点的子节点必为黑色节点</b>。
 * 我们添加新的节点为红色，变色，旋转等操作，其实都是为了维护上面的来两点。<br>
 *
 * <br>还可以看这篇文章再次熟悉红黑树插入的整个流程。<a href="https://www.jianshu.com/p/b7dda385f83d">ConcurrentHashMap与红黑树实现分析Java8</a>
 *
 * <br><br>ps：红黑树中的数据多了之后，黑色节点的个数是远大于红色的。
 *
 * </p><br><br>
 *
 * <p>
 *     红黑树的前身是<b>二三树</b>。二三树有两种节点
 *     <li>节点中有一个键，有两个子节点</li>
 *     <li>节点中有两个键，有三个子节点</li>
 *     <pre></pre>
 *
 *     <br/>
 *     对于二三树的插入操作，先把要插入的键放找到合适的节点插进去，如果该节点的键的个数超过了2个，则将该节点中间位置
 *     的键提到父节点中，现在的情况就像是在父节点中插入了新值，和之前的处理一致。
 *     <br><br>
 *     二三树的高度介于lgN和log_3(N)之间，所有的操作复杂度均在logN以下
 * </p>
 *
 * 为什么黑的稳定? 因为在插入的时候，遇到黑色的不需要改变结构，无需旋转变色。
 * 为什么 每条链路上黑色的一样？ 是为了保证整个二叉树的平衡性，最长的那条支链也不会超过最短的两倍。黑色一样最长的不过是两个黑色中间都是红色，最短的不过是全是黑色。
 *
 * 整个添加的过程中，红黑树趋于怎样，他是怎样的一个流程，红黑节点又会发生什么变化，而又为什么删除旋转最多3次，添加旋转最多2次，而又是怎么趋于 收敛？
 *
 * 仔细分析上面整个插入的过程，复杂的算3、5了，4和3是一致的，scene3能够实现自平衡(无需再将整个树作为新的插入节点)，scene5也就是 父亲叔叔都是
 *   红色，那么就需要变色(变色的结果是两条路径生黑色节点数量没变，父亲节点变成了红色，可以把父亲节点当作新插入的节点重新开始插入)，这一步变色就是
 *   递归了，而5中情况中的一种情况需要递归，所以说是收敛的。
 * 那么再想，这一次遇到了非常特殊的情况，以父节点为插入节点的时候还是scene5，一直向上，最后有两种情况，一遇到了跟节点，然后scene1，跟节点再变会
 *   黑，而这种情况(第1，2层都是黑节点)也导致下一种情况的可能发生。二是遇到了跟节点的前一层节点，直接变红，就完事了。
 * 那么再深入的多想几步，scene3，scene3变换之后就变成了scene5这种情况。
 *
 * 红黑树的删除 是一个比较复杂的过程，手段是旋转和变色，而目的 无非就是为了让路径上黑色数量一直，红色不挨着。具体可以看
 * <a href="https://www.cnblogs.com/qingergege/p/7351659.html">红黑树之删除节点</a>
 *
 * @author wuyingjie
 * @date 2018年6月5日
 */

public class RBTree<T extends Comparable<T>>{
	
	public static class RBNode<N extends Comparable<N>> {
		public boolean isRed;
		public RBNode<N> left;
		public RBNode<N> right;
		public RBNode<N> parent;
		N data;
		
		public RBNode(N data) {
			isRed = true;
			this.data = data;
		}
	}

	public RBNode<T> mRoot;
	
	public RBTree() {
		mRoot = null;
	}
	
	public void insert(T data) {
		//首先用之前在二叉搜索树种使用的 “比到死”方法 插入节点。
		if (mRoot == null) {
			mRoot = new RBNode<>(data);
			mRoot.isRed = false;
			return;
		}
		
		RBNode<T> node = mRoot;
		RBNode<T> parent = null;
		while (node != null) {
			int compared = data.compareTo(node.data);
			if (compared < 0) {
				parent = node;
				node = node.left;
			} else if (compared > 0) {
				parent = node;
				node = node.right;
			} else {
				throw new IllegalArgumentException("该值已存在！！");
			}
		}
		node = new RBNode<>(data);
		node.parent = parent;
		node.isRed = true;
		
		if (data.compareTo(parent.data) < 0) {
			parent.left = node;
		} else {
			parent.right = node;
		}
		
		// 插入完成，重新修整 RBTree 保持红黑树特性
		fixRBTree(node);
		
		mRoot.isRed = false;
	}
	
	private void fixRBTree(RBNode<T> node) {

		if (node == mRoot) {
			mRoot.isRed = false;
			return;
		}

		if (node.parent.isRed) {
			//注意如果parent是红树，那么一定有祖父
			
			if (node.parent.parent.left == node.parent) {
				//如果是parent是祖父的左子树
				
				if (node.parent.parent.right != null && node.parent.parent.right.isRed) {
					//叔叔也是红树
					node.parent.isRed = false;
					node.parent.parent.right.isRed = false;
					node.parent.parent.isRed = true;
					// 以祖父节点为修复节点 开始修复
					fixRBTree(node.parent.parent);
				} else {
					// 叔叔是右黑树
					// 需要根据当前节点在父亲的左边还是右边进行旋转
					if (node.parent.left == node) {
						// 如果在左边，变色 然后根据祖父节点向右旋转，也就是 rotateLL(parent.parent)
						node.parent.isRed = false;
						node.parent.parent.isRed = true;
						rotateLL(node.parent.parent);
					} else {
						// 如果在右边，先根据父节点向左旋转，变成LL形式，也就是上面的那种情况
						// 然后再 变色，旋转
						rotateRR(node.parent);
						// 现在 node 来到了 parent的位置，parent变成了node的左节点
						node = node.left;

						node.parent.isRed = false;
						node.parent.parent.isRed = true;
						rotateLL(node.parent.parent);
					}
					
				}
			} else {
				// parent是祖父的右子树，逻辑与上面基本一致
				if (node.parent.parent.left != null && node.parent.parent.left.isRed) {
					node.parent.isRed = false;
					node.parent.parent.left.isRed = false;
					node.parent.parent.isRed = true;
					fixRBTree(node.parent.parent);
				} else {
					if (node.parent.left == node) {
						rotateLL(node.parent);
						node = node.right;
						
						node.parent.isRed = false;
						node.parent.parent.isRed = true;
						rotateRR(node.parent.parent);
					} else {
						node.parent.isRed = false;
						node.parent.parent.isRed = true;
						rotateRR(node.parent.parent);
					}
				}
			}
			
			
		} else {
			// 如果父亲是黑色的  直接返回
		}
	}
	
	
	private void rotateLL(RBNode<T> tree) {
		RBNode<T> futureTree = tree.left;
		
		if (tree.parent == null) {
			// 说明在旋转根节点
			mRoot = futureTree;
			futureTree.parent = null;
			tree.parent = futureTree;
		} else {
			boolean isLeft = tree.parent.left == tree;
			futureTree.parent = tree.parent;
			if (isLeft) {
				tree.parent.left = futureTree;
			} else {
				tree.parent.right = futureTree;
			}
			
			tree.parent = futureTree;
		}
		
		tree.left = futureTree.right;
		if (futureTree.right != null)
			futureTree.right.parent = tree;
		
		futureTree.right = tree;
	}
	
	private void rotateRR(RBNode<T> tree) {
		RBNode<T> futureTree = tree.right;
		
		if (tree.parent == null) {
			// 说明在旋转根节点
			mRoot = futureTree;
			futureTree.parent = null;
			tree.parent = futureTree;
		} else {
			boolean isLeft = tree.parent.left == tree;
			futureTree.parent = tree.parent;
			if (isLeft) {
				tree.parent.left = futureTree;
			} else {
				tree.parent.right = futureTree;
			}
			
			tree.parent = futureTree;
		}
		
		tree.right = futureTree.left;
		if (futureTree.left != null)
			futureTree.left.parent = tree;
		
		futureTree.left = tree;
		
	}
	
	public void preOrder(RBNode<T> tree) {
		if (tree != null) {
			String color = tree.isRed ? "R" : "B";
			System.out.print("	"+color+tree.data);
			preOrder(tree.left);
			preOrder(tree.right);
		}
	}
	
	public static void main(String[] args) {
		int[] datas = {10, 40, 30, 60, 90, 70, 20, 50, 80};
		RBTree<Integer> tree = new RBTree<>();
		
		for (int item : datas) {
			tree.insert(item);
		}
		
		System.out.println("前序：");
		tree.preOrder(tree.mRoot);
		
	}
	
}
