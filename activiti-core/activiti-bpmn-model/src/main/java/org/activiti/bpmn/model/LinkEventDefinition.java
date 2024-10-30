/*
 * Copyright 2010-2020 Alfresco Software, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.bpmn.model;

import java.util.List;

public class LinkEventDefinition extends EventDefinition{

    protected String id;

    protected String name;

    protected String target;

    protected List<String> sources;

    public LinkEventDefinition clone() {
        LinkEventDefinition clone=new LinkEventDefinition();
        clone.setValues(this);
        return clone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<String> getSources() {
        return this.sources;
    }

    public void setSource(List<String> sources) {
        this.sources = sources;
    }

    public void setValues(LinkEventDefinition otherDefinition) {
        super.setValues(otherDefinition);
        setId(otherDefinition.getId());
        setName(otherDefinition.getName());
        setTarget(otherDefinition.getTarget());
        setSource(otherDefinition.getSources());
    }
}
