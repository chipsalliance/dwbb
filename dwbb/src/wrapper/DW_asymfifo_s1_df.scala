// SPDX-License-Identifier: Apache-2.0
// SPDX-FileCopyrightText: 2024 Jiuyang Liu <liu@jiuyang.me>
package org.chipsalliance.dwbb.wrapper.DW_asymfifo_s1_df

import chisel3.experimental.IntParam
import org.chipsalliance.dwbb.interface.DW_asymfifo_s1_df._
import org.chipsalliance.dwbb.wrapper.WrapperModule

import scala.collection.immutable.SeqMap

class DW_asymfifo_s1_df(parameter: Parameter)
    extends WrapperModule[Interface, Parameter](
      new Interface(parameter),
      parameter,
      p =>
        SeqMap(
          "data_in_width" -> IntParam(p.dataInWidth),
          "data_out_width" -> IntParam(p.dataOutWidth),
          "depth" -> IntParam(p.depth),
          "err_mode" -> IntParam(p.errMode match {
            case "pointer_latched" => 0
            case "latched"         => 1
            case "unlatched"       => 2
          }),
          "rst_mode" -> IntParam(p.rstMode match {
            case "async_with_mem" => 0
            case "sync_with_mem"  => 1
            case "async_wo_mem"   => 2
            case "sync_wo_mem"    => 3
          }),
          "byte_order" -> IntParam(if (p.byteOrder) 1 else 0)
        )
    )
