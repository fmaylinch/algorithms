package com.codethen.algorithms.exercises;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-paths/
 */
public class BinaryTreePaths {

    class Solution {

        public List<String> binaryTreePaths(TreeNode root) {

            if (root == null) return Collections.emptyList();

            List<String> left = binaryTreePaths(root.left);
            List<String> right = binaryTreePaths(root.right);

            List<String> result = new ArrayList<String>();
            for (String path : left) {
                result.add(root.val + "->" + path);
            }
            for (String path : right) {
                result.add(root.val + "->" + path);
            }

            if (result.isEmpty()) {
                result.add("" + root.val);
            }

            return result;
        }
    }
}

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }