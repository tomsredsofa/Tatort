package net.codeplumber.tatort

class KommissarController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        if (!params.sort) {
            params.sort = "lastName"
        }
        [kommissarInstanceList: Kommissar.list(params), kommissarInstanceTotal: Kommissar.count()]
    }

    def create = {
        def kommissarInstance = new Kommissar()
        kommissarInstance.properties = params
        return [kommissarInstance: kommissarInstance]
    }

    def save = {
        def kommissarInstance = new Kommissar(params)
        if (kommissarInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'kommissar.label', default: 'Kommissar'), kommissarInstance.id])}"
            redirect(action: "show", id: kommissarInstance.id)
        }
        else {
            render(view: "create", model: [kommissarInstance: kommissarInstance])
        }
    }

    def show = {
        def kommissarInstance = Kommissar.get(params.id)
        if (!kommissarInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'kommissar.label', default: 'Kommissar'), params.id])}"
            redirect(action: "list")
        }
        else {
            [kommissarInstance: kommissarInstance]
        }
    }

    def edit = {
        def kommissarInstance = Kommissar.get(params.id)
        if (!kommissarInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'kommissar.label', default: 'Kommissar'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [kommissarInstance: kommissarInstance]
        }
    }

    def update = {
        def kommissarInstance = Kommissar.get(params.id)
        if (kommissarInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (kommissarInstance.version > version) {
                    
                    kommissarInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'kommissar.label', default: 'Kommissar')] as Object[], "Another user has updated this Kommissar while you were editing")
                    render(view: "edit", model: [kommissarInstance: kommissarInstance])
                    return
                }
            }
            kommissarInstance.properties = params
            if (!kommissarInstance.hasErrors() && kommissarInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'kommissar.label', default: 'Kommissar'), kommissarInstance.id])}"
                redirect(action: "show", id: kommissarInstance.id)
            }
            else {
                render(view: "edit", model: [kommissarInstance: kommissarInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'kommissar.label', default: 'Kommissar'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def kommissarInstance = Kommissar.get(params.id)
        if (kommissarInstance) {
            try {
                kommissarInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'kommissar.label', default: 'Kommissar'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'kommissar.label', default: 'Kommissar'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'kommissar.label', default: 'Kommissar'), params.id])}"
            redirect(action: "list")
        }
    }
}
