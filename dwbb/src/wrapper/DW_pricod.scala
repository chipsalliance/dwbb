// SPDX-License-Identifier: Apache-2.0
// SPDX-FileCopyrightText: 2024 Jiuyang Liu <liu@jiuyang.me>
package org.chipsalliance.dwbb.wrapper.DW_pricod

import chisel3.experimental.IntParam
import org.chipsalliance.dwbb.interface.DW_pricod._
import org.chipsalliance.dwbb.wrapper.WrapperModule

import scala.collection.immutable.SeqMap

class DW_pricod(parameter: Parameter)
    extends WrapperModule[Interface, Parameter](
      new Interface(parameter),
      parameter,
      p =>
        SeqMap(
          "a_width" -> IntParam(p.aWidth)
        )
    )