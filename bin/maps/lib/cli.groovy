// Parse command line arguments and options
CliBuilder cli = new CliBuilder(usage: 'convert-maps.groovy [options] [output_dir]', header: 'Options:')
cli.s(longOpt: 'silent', 'silent mode, no output to console', required: false)
cli.h(longOpt: 'help', 'shows the help', required: false)
cli.p(longOpt: 'pretty', 'convert maps to formatted json', required: false)
cli.f(longOpt: 'force', 'force override target directory', required: false)
options = cli.parse(this.args)

args = options.arguments()
if (options.help || args.size() > 1) {
    cli.usage()
    System.exit(-1)
}