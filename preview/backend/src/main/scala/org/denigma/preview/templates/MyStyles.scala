package org.denigma.preview.templates

import scalacss.Defaults._

object MyStyles extends StyleSheet.Standalone {
  import dsl._

  "p.desc" - (
    margin(12 px, auto),
    textAlign.left,
    cursor.pointer,
    color.green,

    &.hover -
      cursor.zoomIn,

    &(media.not.handheld.landscape.maxWidth(640 px)) -
      width(400 px),

    &("span") -
      color.red
    )

}