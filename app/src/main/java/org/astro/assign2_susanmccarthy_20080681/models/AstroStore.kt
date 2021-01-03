package org.astro.assign2_susanmccarthy_20080681.models

// used to indicate what values each of the CRUD functions will read in.

interface AstroStore {
    fun findAll(): List<AstroModel>
    fun create(astroEvent: AstroModel)
    fun update(astroEvent: AstroModel)
    fun delete(astroEvent: AstroModel)
}