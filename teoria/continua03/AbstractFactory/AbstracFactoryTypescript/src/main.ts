import { Aplication } from "./aplication"
import { UIFactory, MacFactory, WindowsFactory } from "./uifactory"
// npx tsx src/main.ts 
function configurarAplicacion(os: string): void {
    let factory: UIFactory

    if (os.toLowerCase() === 'windows') {
        factory = new WindowsFactory()
        console.log('Configurando la aplicación para Windows...')

    } else if (os.toLowerCase() === 'mac') {
        factory = new MacFactory()
        console.log('Configurando la aplicación para macOS...')
    
    } else {
        throw new Error('SO no soportado: ' + os)
    
    }

    const app = new Aplication(factory)
    app.pintarUI()
}

console.log('-------------------')
configurarAplicacion('windows')
console.log('-------------------')
configurarAplicacion('mac')
console.log('-------------------')
