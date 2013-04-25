package org.aec.facade;

public class SideFuturelabNorth extends Side
{

    public SideFuturelabNorth()
    {
        name = "FutureLab North";
        startAddr = 842;
        endAddr = 911;
        startRow = 17;
        endRow = 24;
        nrColumns = 13;
        pixelWidth = 2;
        pixelHeight = 1;
        x = 120;
        y = 18;            
        windowAddrs = (new int[][] {
            new int[] {
                842, 843, 844, 845, 846, 847, -1, -1, -1, -1, 
                -1, -1, -1
            }, new int[] {
                848, 849, 850, 851, 852, 853, 854, -1, -1, -1, 
                -1, -1, -1
            }, new int[] {
                855, 856, -1, 857, 858, 859, 860, 861, -1, -1, 
                -1, -1, -1
            }, new int[] {
                862, 863, -1, 864, 865, 866, 867, 868, 869, -1, 
                -1, -1, -1
            }, new int[] {
                870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 
                -1, -1, -1
            }, new int[] {
                880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 
                890, -1, -1
            }, new int[] {
                891, 892, 893, -1, -1, 894, 895, 896, 897, 898, 
                899, 900, -1
            }, new int[] {
                901, 902, 903, -1, -1, 904, 905, 906, 907, 908, 
                909, 910, 911
            }
        });
    }
}
