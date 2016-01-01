package com.example.shems.util.algorithm;

public class Algorithm_WH {
    public static double[] HumanBathBehavior = {0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
    public static double[] Tdesired = {0, 0, 0, 0, 0, 0, 0, 0, 55, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 79, 0, 0};
    public static double T_MAXSET = 80;
    public static double T0 = 28;
    public static double QMAX = 1500 * 3600; // 对应相应的1小时热水器能耗 单位J
    public static double capacity = 0.07;
    public static double k1 = 4187 * 1000 * capacity; // 计算Qw时候的参数k1：Cw*density*capacity
    // capacity为要洗的水量0.07L
    public static double k2 = 1 / 1 / (2 * Math.PI * capacity)
            * Math.log(0.4 / 0.3); // 计算Qw时候的参数k2: L/(2*pi*landa*log(d2/d1))
    static double k3 = 1000000;
    public static double interval = 1000;
    public static double[] Tin = {27.8, 26.9, 25.6, 25.6, 25, 25, 25.6, 25.8,
            27.2, 28.3, 29.4, 31.7, 32.8, 33.6, 34.2, 34.4, 35, 34.4, 33.6,
            32.8, 30.6, 30, 27.8, 26.9};
    public static double[] Tw = new double[24]; // 不算初始
    public static double prices[] = new double[24];
    public static double basepolicy[] = new double[24];

    static double WH_op = 0;
    static double Q_current = 0;// 效率数

    Algorithm_WH() {
        initPrices();
        k2 = k2 * interval;

    }

    private static void initPrices() {
        for (int i = 0; i < 7; i++)
            prices[i] = 0.391;
        for (int i = 8; i < 12; i++)
            prices[i] = 1.194;
        for (int i = 12; i < 20; i++)
            prices[i] = 0.781;
        for (int i = 20; i < 24; i++)
            prices[i] = 1.194;
    }

    // k1*(T[i+1]-T[i])=q[i]-k2*(T[i]-Tin[i])
    public static void run() {
        initPrices();
        k2 = k2 * interval;
        double Action;
        double Future_cost;
        Tw[0] = T0;
        for (int i = 0; i < 23; i++) {
            double Q_min = Double.MAX_VALUE;
            WH_op = basepolicy[i];
            Future_cost = 0;
            Action = 0;
            for (int j = 0; j < 100; j++) {
                Future_cost = 0;
                Action += QMAX / 100;
                Tw[i + 1] = getnextTw(i, Action);
                Base_Policy(i + 1);
                for (int k = i + 1; k < 24; k++) {
                    Future_cost += Cost(k, basepolicy[k]);
                }
                Q_current = Cost(i, Action) + Future_cost;
                if (Q_current < Q_min) {
                    Q_min = Q_current;
                    WH_op = Action;
                }

            }
            if (WH_op != basepolicy[i]) {
                if (WH_op >= 0.99 * QMAX)
                    WH_op = 1 * QMAX;
                if (WH_op <= 0.01 * QMAX)
                    WH_op = 0;
                basepolicy[i] = WH_op;
                Tw[i + 1] = getnextTw(i, WH_op);
                Base_Policy(i + 1);
            } else {
                Tw[i + 1] = getnextTw(i, WH_op);
                Base_Policy(i + 1);
            }
        }
    }

    static double Cost_electricity(int k, double Action) {
        return Action * prices[k];
    }

    static double Cost_ComfortPenalty(int k) {
        if (k == 7 || k == 21)
            return Math.abs(Tw[k] - Tdesired[k]) * k3;
        else
            return 0;
    }

    private static double Cost(int k, double Action) {
        return Cost_electricity(k, Action) + Cost_ComfortPenalty(k);
    }

    private static double getnextTw(int i, double q) {
        return (q - k2 * (Tw[i] - Tin[i])) / k1 + Tw[i];

    }

    private static double getQ(int i) {
        return k1 * (Tw[i + 1] - Tw[i]) + k2 * (Tw[i] - Tin[i]);
    }

    static void Base_Policy(int k)// 从k之后的策略决定
    {
        for (int i = k; i < 23; i++) {
            // do basepolicy
            if (HumanBathBehavior[i] == 0) {
                basepolicy[i] = 0;
                Tw[i + 1] = getnextTw(i, 0);
            } else {
                double tempt = getnextTw(i, QMAX);
                if (tempt > T_MAXSET) {
                    basepolicy[i] = getQ(i);
                } else {
                    basepolicy[i] = T_MAXSET;
                }
            }
        }
    }

    public static void main(String args[]) {
        run();
        for (int i = 0; i < 24; i++) {
            System.out.print("power:" + basepolicy[i]);
            System.out.print("\tTW:" + Tw[i]);
            System.out.println();
        }
    }
}
