package org.cf.forgot.arithmetic.tree;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BalancedTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    boolean result = true;
    public boolean isBalanced(TreeNode root) {
        level(root);
        return result;
    }

    public boolean isLeaf(TreeNode node) {
        return (node.left == null) && (node.right == null);
    }

    public int level(TreeNode node) {
        if(isLeaf(node)) {
            return 1;
        }
        TreeNode left = node.left;
        int lv = level(left);
        int rv = level(node.right);

        if(!result) {
            return 0;
        }
        result = Math.abs(lv - rv) <= 1;

        int level = lv > rv? lv : rv;

        return level + 1;
    }
}
