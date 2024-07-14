package org.dblab.blinddate.common.validation

import org.dblab.blinddate.common.validation.ValidationGroups.FutureGroup
import org.dblab.blinddate.common.validation.ValidationGroups.NotBlankGroup
import org.dblab.blinddate.common.validation.ValidationGroups.NotNullGroup
import org.dblab.blinddate.common.validation.ValidationGroups.PatternGroup
import org.dblab.blinddate.common.validation.ValidationGroups.PositiveGroup
import org.dblab.blinddate.common.validation.ValidationGroups.SizeGroup
import jakarta.validation.GroupSequence

@GroupSequence(value = [NotBlankGroup::class, NotNullGroup::class, PatternGroup::class, SizeGroup::class, FutureGroup::class, PositiveGroup::class])
interface ValidationSequence
