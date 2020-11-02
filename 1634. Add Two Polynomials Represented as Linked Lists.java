https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/

idea: Two Pointers
time complexity: O(n1 + n2)
space complexity: O(n1 + n2)

/**
 * Definition for polynomial singly-linked list.
 * class PolyNode {
 *     int coefficient, power;
 *     PolyNode next = null;
 
 *     PolyNode() {}
 *     PolyNode(int x, int y) { this.coefficient = x; this.power = y; }
 *     PolyNode(int x, int y, PolyNode next) { this.coefficient = x; this.power = y; this.next = next; }
 * }
 */

class Solution {
    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode dummy = new PolyNode(0, 0), curr = dummy;
        while (poly1 != null || poly2 != null) {
            if (poly1 == null) {
                curr.next = poly2;
                break;
            } else if (poly2 == null) {
                curr.next = poly1;
                break;
            } else if (poly1.power > poly2.power) {
                curr.next = poly1;
                curr = curr.next;
                poly1 = poly1.next;
            } else if (poly1.power < poly2.power) {
                curr.next = poly2;
                curr = curr.next;
                poly2 = poly2.next;
            } else {
                int newCoeff = poly1.coefficient + poly2.coefficient;
                System.out.println("newCoeff="+newCoeff);
                if (newCoeff != 0) {
                    PolyNode newNode = new PolyNode(newCoeff, poly1.power);
                    curr.next = newNode;
                    curr = curr.next;
                } else {
                    curr.next = null;
                }
                poly1 = poly1.next;
                poly2 = poly2.next;
            }
        }
        
        return dummy.next;
    }
}