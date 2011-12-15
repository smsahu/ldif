/*
 * LDIF
 *
 * Copyright 2011 Freie Universität Berlin, MediaEvent Services GmbH & Co. KG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ldif.modules.silk.hadoop

import de.fuberlin.wiwiss.silk.hadoop.impl.EntityConfidence
import de.fuberlin.wiwiss.silk.config.LinkSpecification
import org.apache.hadoop.mapreduce.Reducer
import org.apache.hadoop.io.Text
import scala.collection.JavaConversions._

class FilterReduce extends Reducer[Text, EntityConfidence, Text, EntityConfidence] {

  private var linkSpec: LinkSpecification = null

  protected override def setup(context: Reducer[Text, EntityConfidence, Text, EntityConfidence]#Context) {
    linkSpec = Config.readLinkSpec(context.getConfiguration)
  }

  protected override def reduce(sourceUri : Text, entitiySimilarities : java.lang.Iterable[EntityConfidence],
                                context : Reducer[Text, EntityConfidence, Text, EntityConfidence]#Context) {

    linkSpec.filter.limit match {
      case Some(limit) => {
        for(entitySimilarity <- entitiySimilarities.take(limit)) {
          context.write(sourceUri, entitySimilarity)
        }
      }
      case None => {
        for(entitySimilarity <- entitiySimilarities) {
          context.write(sourceUri, entitySimilarity)
        }
      }
    }
  }
}