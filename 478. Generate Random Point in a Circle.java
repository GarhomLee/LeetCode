https://leetcode.com/problems/generate-random-point-in-a-circle/

// 思路：几何题。利用极坐标系。详见https://leetcode.com/problems/generate-random-point-in-a-circle/discuss/154037/Polar-Coordinates-10-lines

class Solution {
    double radius, xCenter, yCenter;
    Random rand;
    
    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        xCenter = x_center;
        yCenter = y_center;
        rand = new Random();
    }
    
    public double[] randPoint() {
        double degree = rand.nextDouble() * 2 * Math.PI;
        double len = Math.sqrt(rand.nextDouble()) * radius;
        double x = xCenter + len * Math.cos(degree);
        double y = yCenter + len * Math.sin(degree);
        return new double[]{x, y};
    }
}
