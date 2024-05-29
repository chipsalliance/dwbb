// SPDX-License-Identifier: Apache-2.0
// SPDX-FileCopyrightText: 2024 Jiuyang Liu <liu@jiuyang.me>
package org.chipsalliance.dwbb.interface.DW_mult_seq

import chisel3._
import chisel3.experimental.SerializableModuleParameter
import upickle.default

object Parameter {
  implicit def rw: default.ReadWriter[Parameter] =
    upickle.default.macroRW[Parameter]
}

case class Parameter(
    aWidth: Int = 8,
    bWidth: Int = 8,
    tcMode: Boolean = false,
    numCyc: Int = 3,
    rstMode: Boolean = false,
    inputMode: Boolean = true,
    outputMode: Boolean = true,
    earlyStart: Boolean = false
) extends SerializableModuleParameter {
  require(bWidth >= 3, "bWidth must be greater than or equal to 3")
  require(
    Range.inclusive(3, bWidth).contains(aWidth),
    s"bWidth must be between 3 and $bWidth"
  )
  require(
    Range.inclusive(3, aWidth).contains(numCyc),
    s"numCyc must be between 3 and $aWidth"
  )
}

class Interface(parameter: Parameter) extends Bundle {
  val clk: Clock = Input(Clock())
  val rst_n: Bool = Input(Bool())
  val hold: Bool = Input(Bool())
  val start: Bool = Input(Bool())
  val a: UInt = Input(UInt(parameter.aWidth.W))
  val b: UInt = Input(UInt(parameter.bWidth.W))
  val complete: Bool = Output(Bool())
  val product: UInt = Output(UInt((parameter.aWidth + parameter.bWidth).W))
}
