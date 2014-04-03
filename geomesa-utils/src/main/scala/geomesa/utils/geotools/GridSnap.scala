package geomesa.utils.geotools

import com.vividsolutions.jts.geom.Envelope

class GridSnap(env: Envelope, xSize: Int, ySize: Int) {

  val dx = env.getWidth / (xSize - 1)
  val dy = env.getHeight / (ySize - 1)

  /**
   * Computes the X ordinate of the i'th grid column.
   * @param i the index of a grid column
   * @return the X ordinate of the column
   */
  def x(i: Int) =
    if (i >= xSize - 1) env.getMaxX
    else env.getMinX + i * dx

  /**
   * Computes the Y ordinate of the i'th grid row.
   * @param j the index of a grid row
   * @return the Y ordinate of the row
   */
  def y(j: Int) =
    if (j >= ySize - 1) env.getMaxY()
    else env.getMinY + j * dy

  /**
   * Computes the column index of an X ordinate.
   * @param v the X ordinate
   * @return the column index
   */
  def i(v: Double): Int = v match {
    case x if x > env.getMaxX => xSize
    case x if x < env.getMinX => -1
    case x =>
      val ret = (x - env.getMinX) / dx
      if (ret >= xSize) xSize - 1
      else ret.toInt
  }

  /**
   * Computes the column index of an Y ordinate.
   * @param v the Y ordinate
   * @return the column index
   */
  def j(v: Double): Int = v match {
    case y if y > env.getMaxY => ySize
    case y if y < env.getMinY => -1
    case y =>
      val ret = (y - env.getMinY) / dy
      if (ret >= ySize) ySize - 1
      else ret.toInt
  }

}
