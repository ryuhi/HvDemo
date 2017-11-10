package com.ryuhi.demo.HvDemo.validator.group.bizArea;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import com.ryuhi.demo.HvDemo.validator.group.inner.First;
import com.ryuhi.demo.HvDemo.validator.group.inner.Second;
import com.ryuhi.demo.HvDemo.validator.group.inner.Third;

@GroupSequence({Default.class, First.class, Second.class, Third.class})
public interface User {

}
