package com.wyj.ds_algorithm.tree.imp;

/**
 * <b>红黑树</b>
 * <p>一： 红黑树的特点
 * <li>节点 非红即黑</li>
 * <li>根节点为黑色节点</li>
 * <li>每个(为null的)叶子节点是黑色(ps:所有的叶子节点都是null)</li>
 * <li>红色节点的子节点必为黑色节点</li>
 * <li>任何节点到其所有子节点的黑色节点的个数相同</li>
 * <br>若满足了上述所有条件，那么这个二叉排序树就是红黑树。
 * <br><b>RBTree红黑树</b>也是一颗较为平衡的二叉排序树，较<b>AVLTree二叉平衡树</b>而言，
 * 插入操作的旋转次数要小的多，AVLTree适合查询多，插入删除操作较少的情况。RBTree相当于是对插入删除
 * 操作的优化，降低了旋转的次数，提高了效率。
 * </p><br>
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
 * 我们添加新的节点为红色，变色，旋转等操作，其实都是为了维护上面的来两点。
 * 
 * </p><br>
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
