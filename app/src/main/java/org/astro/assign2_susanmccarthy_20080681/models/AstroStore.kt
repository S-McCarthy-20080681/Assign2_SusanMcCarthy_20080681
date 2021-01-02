package org.astro.assign2_susanmccarthy_20080681.models

interface AstroStore {
    fun findAll(): List<AstroModel>
    fun create(astroEvent: AstroModel)
    fun update(astroEvent: AstroModel)
    fun delete(astroEvent: AstroModel)
}