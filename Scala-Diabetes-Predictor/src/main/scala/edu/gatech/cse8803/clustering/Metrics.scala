/**
 * @author Tian Tan <ttan40@gatech.edu>.
 */

package edu.gatech.cse8803.clustering

import org.apache.spark.rdd.RDD

object Metrics {
  /**
   * Given input RDD with tuples of assigned cluster id by clustering,
   * and corresponding real class. Calculate the purity of clustering.
   * Purity is defined as
   *             \fract{1}{N}\sum_K max_j |w_k \cap c_j|
   * where N is the number of samples, K is number of clusters and j
   * is index of class. w_k denotes the set of samples in k-th cluster
   * and c_j denotes set of samples of class j.
   * @param clusterAssignmentAndLabel RDD in the tuple format
   *                                  (assigned_cluster_id, class)
   * @return purity
   */
  def purity(clusterAssignmentAndLabel: RDD[(Int, Int)]): Double = {
    /**
      * TODO: Remove the placeholder and implement your code here
      */
    clusterAssignmentAndLabel.map(line => ((line._1, line._2), 1)).keyBy(_._1).reduceByKey((x, y) => (x._1, x._2 + y._2)).map(f => (f._2._1._1, f._2._2)).keyBy(_._1).reduceByKey((x, y) => (1, math.max(x._2, y._2))).map(v => v._2._2).reduce(_+_)/clusterAssignmentAndLabel.count().toDouble
  }
}
