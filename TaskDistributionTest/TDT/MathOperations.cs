using System;
using System.Threading;

namespace TDT
{
    public static class MathOperations
    {
        public static double MathTransform1(double num)
        {
            return Math.Sqrt(num);
        }

        public static double MathTransform2(double num)
        {
            var x = Math.Sqrt(num);
            var x2 = x + num;
            var x3 = Math.Sqrt(x2);
            var x4 = Math.Pow(x3, 1.5);
            var x5 = x4 + x;
            var x6 = Math.Sqrt(x5);
            var hash = x6.GetHashCode();
            var str = hash.ToString();
            var x7 = int.Parse(str);
            var x8 = Math.Sqrt(x7) + Math.Sqrt(int.Parse(str));
            var x9 = Math.Sqrt(x8);
            var x10 = Math.Asinh(x9);
            var x11 = Math.Cbrt(x10);
            var x12 = Math.Pow(x11, 2.76);
            var str2 = x12.ToString();
            var x13 = double.Parse(str2);

            return x13;
        }

    }
}
